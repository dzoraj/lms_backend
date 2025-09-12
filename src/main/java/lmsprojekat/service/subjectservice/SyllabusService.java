package lmsprojekat.service.subjectservice;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.subjectdto.LearningOutcomeDTO;
import lmsprojekat.model.subject.LearningOutcome;
import lmsprojekat.model.subject.Subject;
import lmsprojekat.repository.subjectrepo.LearningOutcomeRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.repository.teachingrepo.TeacherOnCourseRepository;
@Service
public class SyllabusService {

    private final LearningOutcomeRepository learningOutcomeRepository;
    private final TeacherOnCourseRepository teacherOnCourseRepository;
    private final SubjectRepository subjectRepository;

    private final LearningOutcomeService learningOutcomeService;

    public SyllabusService(LearningOutcomeRepository learningOutcomeRepository,
                           TeacherOnCourseRepository teacherOnCourseRepository,
                           LearningOutcomeService learningOutcomeService, SubjectRepository subjectRepository) {
        this.learningOutcomeRepository = learningOutcomeRepository;
        this.teacherOnCourseRepository = teacherOnCourseRepository;
        this.learningOutcomeService = learningOutcomeService;
        this.subjectRepository = subjectRepository;
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
}
