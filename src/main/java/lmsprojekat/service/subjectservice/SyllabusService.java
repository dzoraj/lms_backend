package lmsprojekat.service.subjectservice;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.dto.teachingdto.TeachingSessionDTO;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.model.teaching.TeachingSession;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;
import lmsprojekat.repository.teachingrepo.TeachingSessionRepository;
import lmsprojekat.service.teachingservice.TeachingSessionService;

@Service
public class SyllabusService {

    private final LearningOutcomeRepository learningOutcomeRepository;
    private final TeacherOnCourseRepository teacherOnCourseRepository;
    private final SubjectRepository subjectRepository;
    private final TeachingSessionRepository teachingSessionRepository;

    private final LearningOutcomeService learningOutcomeService;
    private final TeachingSessionService teachingSessionService;

    public SyllabusService(LearningOutcomeRepository learningOutcomeRepository,
                           TeacherOnCourseRepository teacherOnCourseRepository,
                           LearningOutcomeService learningOutcomeService,
                           SubjectRepository subjectRepository,
                           TeachingSessionRepository teachingSessionRepository,
                           TeachingSessionService teachingSessionService) {
        this.learningOutcomeRepository = learningOutcomeRepository;
        this.teacherOnCourseRepository = teacherOnCourseRepository;
        this.learningOutcomeService = learningOutcomeService;
        this.subjectRepository = subjectRepository;
        this.teachingSessionRepository = teachingSessionRepository;
        this.teachingSessionService = teachingSessionService;
    }

    public List<LearningOutcomeDTO> getSyllabusRestricted(Long teacherId, Long subjectId) {
        if (!teacherOnCourseRepository.existsByTeacherAndSubject(teacherId, subjectId)) {
            throw new SecurityException("You are not authorized to view this syllabus.");
        }

        return learningOutcomeRepository.findBySubject_Id(subjectId)
                .stream()
                .filter(lo -> !lo.isDeleted()) 
                .map(learningOutcomeService::toDTO)
                .toList();
    }

    public LearningOutcomeDTO saveOutcome(Long teacherId, Long subjectId, LearningOutcomeDTO dto) {
        if (!teacherOnCourseRepository.existsByTeacherAndSubject(teacherId, subjectId)) {
            throw new SecurityException("You are not authorized to edit this syllabus.");
        }

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID " + subjectId));

        LearningOutcome entity = learningOutcomeService.toEntity(dto);
        entity.setSubject(subject);

        return learningOutcomeService.toDTO(learningOutcomeRepository.save(entity));
    }

    public void deleteOutcome(Long teacherId, Long subjectId, Long outcomeId) {
        if (!teacherOnCourseRepository.existsByTeacherAndSubject(teacherId, subjectId)) {
            throw new SecurityException("You are not authorized to delete from this syllabus.");
        }

        var outcome = learningOutcomeRepository.findById(outcomeId)
                .orElseThrow(() -> new IllegalArgumentException("Outcome not found"));

        if (!outcome.getSubject().getId().equals(subjectId)) {
            throw new IllegalArgumentException("This outcome does not belong to the given subject.");
        }

        outcome.setDeleted(true);
        learningOutcomeRepository.save(outcome);
    }

    public TeachingSessionDTO assignOutcomesToSession(Long teacherId, Long subjectId, Long sessionId, List<Long> outcomeIds) {
        if (!teacherOnCourseRepository.existsByTeacherAndSubject(teacherId, subjectId)) {
            throw new SecurityException("You are not authorized to edit this syllabus.");
        }

        TeachingSession session = teachingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("TeachingSession not found: " + sessionId));

        if (!session.getCourseRealization().getSubject().getId().equals(subjectId)) {
            throw new IllegalArgumentException("Session does not belong to this subject.");
        }

        List<LearningOutcome> outcomes = outcomeIds.stream()
                .map(id -> learningOutcomeRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("LearningOutcome not found: " + id)))
                .toList();


        session.getLearningOutcomes().clear();
        session.getLearningOutcomes().addAll(outcomes);

        TeachingSession saved = teachingSessionRepository.save(session);
        return teachingSessionService.toDTO(saved);
    }


    public List<LearningOutcomeDTO> getOutcomesForSession(Long teacherId, Long subjectId, Long sessionId) {
        if (!teacherOnCourseRepository.existsByTeacherAndSubject(teacherId, subjectId)) {
            throw new SecurityException("You are not authorized to view this syllabus.");
        }

        TeachingSession session = teachingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("TeachingSession not found: " + sessionId));

        return session.getLearningOutcomes().stream()
                .filter(lo -> !lo.isDeleted())
                .map(learningOutcomeService::toDTO)
                .toList();
    }

    public TeachingSessionDTO removeOutcomeFromSession(Long teacherId, Long subjectId, Long sessionId, Long outcomeId) {
        if (!teacherOnCourseRepository.existsByTeacherAndSubject(teacherId, subjectId)) {
            throw new SecurityException("You are not authorized to edit this syllabus.");
        }

        TeachingSession session = teachingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("TeachingSession not found: " + sessionId));

        session.getLearningOutcomes().removeIf(lo -> lo.getId().equals(outcomeId));

        TeachingSession saved = teachingSessionRepository.save(session);
        return teachingSessionService.toDTO(saved);
    }
}
