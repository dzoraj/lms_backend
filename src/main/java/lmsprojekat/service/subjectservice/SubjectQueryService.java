package lmsprojekat.service.subjectservice;


import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lmsprojekat.dto.subjectdto.SubjectDTO;
import lmsprojekat.repository.studentrepo.StudyProgramRepository;
import lmsprojekat.repository.subjectrepo.SubjectRepository;
import lmsprojekat.repository.userrepo.UserRepository;

@Service
@Transactional(readOnly = true)
public class SubjectQueryService {

  private final StudyProgramRepository spRepo;
  private final SubjectRepository subjectRepo;
  private final UserRepository userRepo;

  private final SubjectService subjectService;
  private final LearningOutcomeService learningOutcomeService;

  public SubjectQueryService(
      StudyProgramRepository spRepo,
      SubjectRepository subjectRepo,
      UserRepository userRepo,
      SubjectService subjectService,
      LearningOutcomeService learningOutcomeService
  ) {
    this.spRepo = spRepo;
    this.subjectRepo = subjectRepo;
    this.userRepo = userRepo;
    this.subjectService = subjectService;
    this.learningOutcomeService = learningOutcomeService;
  }

  public Map<String, Object> getStudyProgramOverview(Long programId) {
    var sp = spRepo.fetchWithLeader(programId)
        .orElseThrow(() -> new EntityNotFoundException("StudyProgram not found: " + programId));

    Long leaderId = (sp.getLeader() != null) ? sp.getLeader().getId() : null;
    String leaderName = (leaderId != null) ? userRepo.getDisplayName(leaderId) : null;

    var subjectDTOs = subjectRepo.findAllByStudyProgramId(programId).stream()
        .map(subjectService::toDTO)  
        .toList();

    return Map.of(
        "id", sp.getId(),
        "name", sp.getName(),
        "leaderId", leaderId,
        "leaderName", leaderName,
        "subjects", subjectDTOs
    );
  }

  public SubjectDTO getSubjectFull(Long subjectId) {
    var subject = subjectRepo.fetchWithSyllabus(subjectId);
    if (subject == null) throw new EntityNotFoundException("Subject not found: " + subjectId);

    var dto = subjectService.toDTO(subject);

    if (subject.getSyllabus() != null) {
      var loDtos = subject.getSyllabus().stream()
          .filter(lo -> !Boolean.TRUE.equals(lo.isDeleted()))
          .map(learningOutcomeService::toDTO) 
          .toList();
      dto.setSyllabus(loDtos);
    } else {
      dto.setSyllabus(java.util.List.of());
    }

    return dto;
  }
}
