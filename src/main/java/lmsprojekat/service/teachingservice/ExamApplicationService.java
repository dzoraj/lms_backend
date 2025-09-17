package lmsprojekat.service.teachingservice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lmsprojekat.dto.teachingdto.ExamApplicationDTO;
import lmsprojekat.dto.teachingdto.UpcomingExamDTO;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.teaching.ExamApplication;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.SoftDeleteRepository;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.teachingrepo.ExamApplicationRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.AbstractCrudService;

@Service
public class ExamApplicationService extends AbstractCrudService<ExamApplicationDTO, ExamApplication, Long> {

    private final ExamApplicationRepository examApplicationRepository;
    private final StudentInYearRepository studentInYearRepository;
    private final KnowledgeEvaluationRepository knowledgeEvaluationRepository;

    public ExamApplicationService(
        ExamApplicationRepository examApplicationRepository,
        StudentInYearRepository studentInYearRepository,
        KnowledgeEvaluationRepository knowledgeEvaluationRepository
    ) {
        this.examApplicationRepository = examApplicationRepository;
        this.studentInYearRepository = studentInYearRepository;
        this.knowledgeEvaluationRepository = knowledgeEvaluationRepository;
    }

    @Override
    protected SoftDeleteRepository<ExamApplication, Long> getRepository() {
        return examApplicationRepository;
    }

    @Override
    protected ExamApplicationDTO toDTO(ExamApplication entity) {
        ExamApplicationDTO dto = new ExamApplicationDTO();
        dto.setId(entity.getId());
        dto.setApplicationDate(entity.getApplicationDate());
        dto.setStudentInYearId(entity.getStudentInYear().getId());
        dto.setKnowledgeEvaluationId(entity.getKnowledgeEvaluation().getId());
        dto.setStudentName(entity.getStudentInYear().getStudent().getName());
        dto.setIndexNumber(entity.getStudentInYear().getIndexNumber());
        dto.setExamDate(entity.getKnowledgeEvaluation().getStartTime());
        dto.setEvaluationType(entity.getKnowledgeEvaluation().getEvaluationType().getName());
        return dto;
    }

    @Override
    protected ExamApplication toEntity(ExamApplicationDTO dto) {
        StudentInYear studentInYear = studentInYearRepository.findById(dto.getStudentInYearId())
            .orElseThrow(() -> new IllegalArgumentException("StudentInYear not found"));
        KnowledgeEvaluation evaluation = knowledgeEvaluationRepository.findById(dto.getKnowledgeEvaluationId())
            .orElseThrow(() -> new IllegalArgumentException("KnowledgeEvaluation not found"));

        return new ExamApplication(studentInYear, evaluation);
    }

    @Override
    protected void updateEntity(ExamApplication entity, ExamApplicationDTO dto) { }

    public List<UpcomingExamDTO> getUpcomingExamsForStudent(Long studentId) {
        StudentInYear studentInYear = studentInYearRepository
            .findTopByStudent_IdOrderByEnrollmentDateDesc(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Student not enrolled"));

        List<KnowledgeEvaluation> evaluations =
            knowledgeEvaluationRepository.findByCourseRealization_Subject_StudyYear_Id(
                studentInYear.getStudyYear().getId()
            );

        List<ExamApplication> apps =
            examApplicationRepository.findByStudentInYear(studentInYear);

        Set<Long> appliedIds = apps.stream()
                                   .map(app -> app.getKnowledgeEvaluation().getId())
                                   .collect(Collectors.toSet());

        return evaluations.stream()
            .map(ev -> new UpcomingExamDTO(
                ev.getId(),
                ev.getCourseRealization().getSubject().getName(),
                ev.getStartTime(),
                ev.getEndTime(),
                ev.getEvaluationType().getName(),
                appliedIds.contains(ev.getId())
            ))
            .collect(Collectors.toList());
    }

    public List<ExamApplicationDTO> findByTeacherAndSubject(Long teacherId, Long subjectId) {
        return examApplicationRepository.findByTeacherAndSubject(teacherId, subjectId)
                .stream()
                .map(this::toDTO)
                .toList();
    }
}
