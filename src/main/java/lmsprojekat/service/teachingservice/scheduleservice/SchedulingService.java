package lmsprojekat.service.teachingservice.scheduleservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.teachingdto.schedule.ConflictDetailDTO;
import lmsprojekat.dto.teachingdto.schedule.ConflictProbeDTO;
import lmsprojekat.dto.teachingdto.schedule.ConflictResultDTO;
import lmsprojekat.dto.teachingdto.schedule.RecurringTeachingSessionRequest;
import lmsprojekat.dto.teachingdto.schedule.ScheduleItemType;
import lmsprojekat.dto.teachingdto.schedule.UnifiedScheduleItemDTO;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.repository.subjectrepo.CourseRealizationRepository;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.repository.teachingrepo.TeachingSessionRepository;
import lmsprojekat.repository.teachingrepo.TeachingTypeRepository;

@Service
public class SchedulingService {

    private final TeachingSessionRepository tsRepo;
    private final KnowledgeEvaluationRepository keRepo;
    private final CourseRealizationRepository crRepo;
    private final TeachingTypeRepository ttRepo;
    private final LearningOutcomeRepository loRepo;

    public SchedulingService(TeachingSessionRepository tsRepo,
                             KnowledgeEvaluationRepository keRepo,
                             CourseRealizationRepository crRepo,
                             TeachingTypeRepository ttRepo,
                             LearningOutcomeRepository loRepo) {
        this.tsRepo = tsRepo;
        this.keRepo = keRepo;
        this.crRepo = crRepo;
        this.ttRepo = ttRepo;
        this.loRepo = loRepo;
    }


    public List<UnifiedScheduleItemDTO> unifiedByStudyYear(Long studyYearId, LocalDateTime from, LocalDateTime to) {
        var classes = tsRepo.findByCourseRealization_Subject_StudyYear_IdAndStartTimeBetween(studyYearId, from, to)
                .stream()
                .map(ts -> new UnifiedScheduleItemDTO(
                        ScheduleItemType.TEACHING_SESSION,
                        ts.getId(),
                        ts.getStartTime(),
                        ts.getEndTime(),
                        ts.getCourseRealization() != null ? ts.getCourseRealization().getId() : null,
                        ts.getCourseRealization() != null && ts.getCourseRealization().getSubject()!=null
                                ? ts.getCourseRealization().getSubject().getName() : null,
                        ts.getTeachingType()!=null ? ts.getTeachingType().getName() : null
                ))
                .toList();

        var exams = keRepo.findByStudyYearAndRange(studyYearId, from, to)
                .stream()
                .map(ke -> new UnifiedScheduleItemDTO(
                        ScheduleItemType.KNOWLEDGE_EVALUATION,
                        ke.getId(),
                        ke.getStartTime(),
                        ke.getEndTime(),
                        ke.getCourseRealization()!=null ? ke.getCourseRealization().getId() : null,
                        ke.getCourseRealization()!=null && ke.getCourseRealization().getSubject()!=null
                                ? ke.getCourseRealization().getSubject().getName() : null,
                        labelFor(ke)
                ))
                .toList();

        return new ArrayList<UnifiedScheduleItemDTO>() {{
            addAll(classes);
            addAll(exams);
            sort(Comparator.comparing(UnifiedScheduleItemDTO::getStartTime));
        }};
    }

    private String labelFor(KnowledgeEvaluation ke) {
        var t = ke.getEvaluationType() != null ? ke.getEvaluationType().getName() : "";
        var i = ke.getEvaluationInstrument() != null ? ke.getEvaluationInstrument().getName() : "";
        return (t + (t!=null && !t.isBlank() && i!=null && !i.isBlank() ? " • " : "") + i).trim();
    }


    @Transactional
    public List<Long> createRecurringTeachingSessions(RecurringTeachingSessionRequest req) {
        var cr = crRepo.findById(req.getCourseRealizationId())
                .orElseThrow(() -> new EntityNotFoundException("CourseRealization not found"));
        var tt = ttRepo.findById(req.getTeachingTypeId())
                .orElseThrow(() -> new EntityNotFoundException("TeachingType not found"));

        Set<LocalDate> skip = req.getSkipDates() != null
                ? new HashSet<>(req.getSkipDates())
                : Collections.emptySet();

        List<Long> createdIds = new ArrayList<>();
        LocalDate cursor = req.getStartDate();

        while (!cursor.isAfter(req.getEndDate())) {
            if (req.getDaysOfWeek().contains(cursor.getDayOfWeek()) && !skip.contains(cursor)) {
                LocalDateTime start = LocalDateTime.of(cursor, req.getStartTime());
                LocalDateTime end = LocalDateTime.of(cursor, req.getEndTime());

                boolean overlaps = !tsRepo.findOverlaps(cr.getId(), start, end).isEmpty()
                                 || !keRepo.findOverlaps(cr.getId(), start, end).isEmpty();
                if (!overlaps) {
                    TeachingSession ts = new TeachingSession();
                    ts.setCourseRealization(cr);
                    ts.setTeachingType(tt);
                    ts.setStartTime(start);
                    ts.setEndTime(end);

                    if (req.getLearningOutcomeIds() != null && !req.getLearningOutcomeIds().isEmpty()) {
                        var los = req.getLearningOutcomeIds().stream()
                                .map(id -> loRepo.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found id="+id)))
                                .collect(Collectors.toList());
                        ts.setLearningOutcomes(los);
                    }

                    tsRepo.save(ts);
                    createdIds.add(ts.getId());
                }
            }
            cursor = cursor.plusDays(1);
        }
        return createdIds;
    }

    //CHECKING conflicts
    public ConflictResultDTO checkConflicts(List<ConflictProbeDTO> probes) {
        ConflictResultDTO result = new ConflictResultDTO();

        for (var p : probes) {
            // same-course overlaps
            if (p.getCourseRealizationId() != null) {
                var tsOver = tsRepo.findOverlaps(p.getCourseRealizationId(), p.getStart(), p.getEnd());
                tsOver.forEach(ts -> {
                    result.setHasConflicts(true);
                    result.getDetails().add(new ConflictDetailDTO(
                            "sameCourseOverlap", ScheduleItemType.TEACHING_SESSION, ts.getId(),
                            "Clashes with class: " + ts.getStartTime() + "–" + ts.getEndTime()));
                });
                var keOver = keRepo.findOverlaps(p.getCourseRealizationId(), p.getStart(), p.getEnd());
                keOver.forEach(ke -> {
                    result.setHasConflicts(true);
                    result.getDetails().add(new ConflictDetailDTO(
                            "sameCourseOverlap", ScheduleItemType.KNOWLEDGE_EVALUATION, ke.getId(),
                            "Clashes with exam: " + ke.getStartTime() + "–" + ke.getEndTime()));
                });
            }

            // teacher overlaps 
            if (p.getTeacherId() != null) {
                tsRepo.findTeacherOverlaps(p.getTeacherId(), p.getStart(), p.getEnd()).forEach(ts -> {
                    result.setHasConflicts(true);
                    result.getDetails().add(new ConflictDetailDTO(
                            "teacherOverlap", ScheduleItemType.TEACHING_SESSION, ts.getId(),
                            "Teacher busy (class): " + ts.getStartTime() + "–" + ts.getEndTime()));
                });
                keRepo.findTeacherOverlaps(p.getTeacherId(), p.getStart(), p.getEnd()).forEach(ke -> {
                    result.setHasConflicts(true);
                    result.getDetails().add(new ConflictDetailDTO(
                            "teacherOverlap", ScheduleItemType.KNOWLEDGE_EVALUATION, ke.getId(),
                            "Teacher busy (exam): " + ke.getStartTime() + "–" + ke.getEndTime()));
                });
            }
        }
        return result;
    }
}
