package lmsprojekat.service.quizservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lmsprojekat.dto.quizdto.QuizDefinitionDTO;
import lmsprojekat.dto.quizdto.QuizOptionDTO;
import lmsprojekat.dto.quizdto.QuizQuestionDTO;
import lmsprojekat.dto.quizdto.QuizSubmissionDTO;
import lmsprojekat.dto.teachingdto.EvaluationAttemptDTO;
import lmsprojekat.model.quiz.QuizDefinition;
import lmsprojekat.model.quiz.QuizOption;
import lmsprojekat.model.quiz.QuizQuestion;
import lmsprojekat.model.student.StudentInYear;
import lmsprojekat.model.teaching.EvaluationAttempt;
import lmsprojekat.model.teaching.KnowledgeEvaluation;
import lmsprojekat.repository.quizrepo.QuizDefinitionRepository;
import lmsprojekat.repository.quizrepo.QuizOptionRepository;
import lmsprojekat.repository.quizrepo.QuizQuestionRepository;
import lmsprojekat.repository.studentrepo.StudentInYearRepository;
import lmsprojekat.repository.teachingrepo.EvaluationAttemptRepository;
import lmsprojekat.repository.teachingrepo.KnowledgeEvaluationRepository;
import lmsprojekat.service.teachingservice.EvaluationAttemptService;

@Service
public class QuizService {

    private final QuizDefinitionRepository quizDefRepo;
    private final QuizQuestionRepository questionRepo;
    private final QuizOptionRepository optionRepo;
    private final KnowledgeEvaluationRepository keRepo;
    private final StudentInYearRepository siyRepo;
    private final EvaluationAttemptRepository attemptRepo;
    private final EvaluationAttemptService attemptService;

    public QuizService(QuizDefinitionRepository quizDefRepo,
                       QuizQuestionRepository questionRepo,
                       QuizOptionRepository optionRepo,
                       KnowledgeEvaluationRepository keRepo,
                       StudentInYearRepository siyRepo,
                       EvaluationAttemptRepository attemptRepo,
                       EvaluationAttemptService attemptService) {
        this.quizDefRepo = quizDefRepo;
        this.questionRepo = questionRepo;
        this.optionRepo = optionRepo;
        this.keRepo = keRepo;
        this.siyRepo = siyRepo;
        this.attemptRepo = attemptRepo;
        this.attemptService = attemptService;
    }

    @Transactional
    public QuizDefinitionDTO getByEvaluation(Long knowledgeEvaluationId) {
        // If NONE, create an empty QuizDefinition for this KE
        QuizDefinition def = quizDefRepo.findByKnowledgeEvaluation_IdAndDeletedFalse(knowledgeEvaluationId)
            .orElseGet(() -> {
                KnowledgeEvaluation ke = keRepo.findById(knowledgeEvaluationId)
                    .orElseThrow(() -> new EntityNotFoundException("KnowledgeEvaluation not found " + knowledgeEvaluationId));
                QuizDefinition n = new QuizDefinition();
                n.setKnowledgeEvaluation(ke);
                n.setTitle(ke.getEvaluationType() != null ? ke.getEvaluationType().getName() + " Quiz" : "Quiz");
                n.setInstructions("");
                n.setActive(true);
                return quizDefRepo.save(n);
            });

        List<QuizQuestion> questions = questionRepo.findByQuizIdOrdered(def.getId());
        List<Long> qIds = questions.stream().map(QuizQuestion::getId).toList();
        Map<Long, List<QuizOption>> options = qIds.isEmpty()
            ? Map.of()
            : optionRepo.findByQuestionIds(qIds).stream()
                .collect(Collectors.groupingBy(o -> o.getQuestion().getId()));

        KnowledgeEvaluation ke = def.getKnowledgeEvaluation();

        List<QuizQuestionDTO> qdtos = new ArrayList<>();
        for (QuizQuestion q : questions) {
            List<QuizOptionDTO> odtos = (options.getOrDefault(q.getId(), List.of())).stream()
                .sorted(Comparator.comparing(QuizOption::getOrderIndex).thenComparing(QuizOption::getId))
                .map(o -> new QuizOptionDTO(o.getId(), o.getText(), o.isCorrect(), o.getOrderIndex()))
                .toList();
            qdtos.add(new QuizQuestionDTO(q.getId(), q.getText(), q.getType(), q.getPoints(), q.getOrderIndex(), odtos));
        }

        return new QuizDefinitionDTO(
            def.getId(),
            ke.getId(),
            def.getTitle(),
            def.getInstructions(),
            ke.getPoints(),          
            def.isActive(),
            qdtos                    // empty list on first call
        );
    }


    @Transactional
    public QuizDefinitionDTO putDefinition(Long knowledgeEvaluationId, QuizDefinitionDTO dto) {
        KnowledgeEvaluation ke = keRepo.findById(knowledgeEvaluationId)
                .orElseThrow(() -> new EntityNotFoundException("KnowledgeEvaluation not found " + knowledgeEvaluationId));

        int sumPoints = dto.getQuestions() == null ? 0 :
                dto.getQuestions().stream().map(QuizQuestionDTO::getPoints).filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
        if (ke.getPoints() == null || !Objects.equals(ke.getPoints(), sumPoints)) {
            throw new IllegalArgumentException("Sum of question points (" + sumPoints + ") must equal KnowledgeEvaluation.points (" + ke.getPoints() + ")");
        }

        if (dto.getQuestions() == null || dto.getQuestions().isEmpty()) {
            throw new IllegalArgumentException("Quiz must contain at least one question");
        }

        validateQuestions(dto.getQuestions());

        QuizDefinition def = quizDefRepo.findByKnowledgeEvaluation_IdAndDeletedFalse(knowledgeEvaluationId).orElse(null);
        if (def == null) {
            def = new QuizDefinition();
            def.setKnowledgeEvaluation(ke);
        }
        def.setTitle(dto.getTitle());
        def.setInstructions(dto.getInstructions());
        def.setActive(dto.getActive() != null ? dto.getActive() : true);
        quizDefRepo.save(def);

        List<QuizQuestion> existing = questionRepo.findByQuizIdOrdered(def.getId());
        for (QuizQuestion q : existing) {
            List<QuizOption> opts = optionRepo.findByQuestionIdOrdered(q.getId());
            for (QuizOption o : opts) { o.setDeleted(true); optionRepo.save(o); }
            q.setDeleted(true); questionRepo.save(q);
        }

        for (QuizQuestionDTO qdto : dto.getQuestions()) {
            QuizQuestion q = new QuizQuestion();
            q.setQuiz(def);
            q.setText(qdto.getText());
            q.setType(normalizeType(qdto.getType()));
            q.setPoints(qdto.getPoints());
            q.setOrderIndex(qdto.getOrderIndex() != null ? qdto.getOrderIndex() : 0);
            questionRepo.save(q);

            int idx = 0;
            for (QuizOptionDTO odto : (qdto.getOptions() == null ? List.<QuizOptionDTO>of() : qdto.getOptions())) {
                QuizOption o = new QuizOption();
                o.setQuestion(q);
                o.setText(odto.getText());
                o.setCorrect(Boolean.TRUE.equals(odto.getCorrect()));
                o.setOrderIndex(odto.getOrderIndex() != null ? odto.getOrderIndex() : idx++);
                optionRepo.save(o);
            }
        }

        return getByEvaluation(knowledgeEvaluationId);
    }

    @Transactional
    public EvaluationAttemptDTO submitAndAutoPublish(QuizSubmissionDTO submission) {
        QuizDefinitionDTO def = getByEvaluation(submission.getKnowledgeEvaluationId());
        if (Boolean.FALSE.equals(def.getActive())) throw new IllegalStateException("Quiz is not active");

        StudentInYear siy = siyRepo.findById(submission.getStudentInYearId())
                .orElseThrow(() -> new EntityNotFoundException("StudentInYear not found " + submission.getStudentInYearId()));

        if (attemptRepo.existsByEvaluation_IdAndStudentInYear_Id(submission.getKnowledgeEvaluationId(), submission.getStudentInYearId())) {
            //  mark previous as not latest:
            KnowledgeEvaluation ke = keRepo.findById(submission.getKnowledgeEvaluationId()).orElseThrow();
            attemptRepo.markOldAttemptsAsNotLatest(siy.getId(), ke.getCourseRealization().getId(), ke.getEvaluationType().getId());
        }

        Map<Long, Set<Long>> selectedByQuestion = new HashMap<>();
        if (submission.getAnswers() != null) {
            for (QuizSubmissionDTO.SubmittedAnswerDTO a : submission.getAnswers()) {
                selectedByQuestion.put(a.getQuestionId(),
                        a.getSelectedOptionIds() == null ? Set.of() : new HashSet<>(a.getSelectedOptionIds()));
            }
        }

        int total = 0;
        for (QuizQuestionDTO q : def.getQuestions()) {
            Set<Long> selected = selectedByQuestion.getOrDefault(q.getId(), Set.of());
            int pts = scoreQuestion(q, selected);
            total += pts;
        }

        EvaluationAttempt attempt = new EvaluationAttempt();
        attempt.setEvaluation(keRepo.findById(def.getKnowledgeEvaluationId()).orElseThrow());
        attempt.setStudentInYear(siy);
        attempt.setPoints(total);
        attempt.setNote("Auto-graded quiz submission");
        attempt.setLatest(true);
        attemptRepo.save(attempt);

        attemptService.updateFinalSubjectGrade(
                siy.getStudent().getId(),
                attempt.getEvaluation().getCourseRealization().getSubject().getId()
        );

        return new EvaluationAttemptDTO(
                attempt.getId(),
                attempt.getPoints(),
                attempt.getNote(),
                attempt.isLatest(),
                attempt.getEvaluation().getId(),
                attempt.getStudentInYear().getId()
        );
    }

    private void validateQuestions(List<QuizQuestionDTO> questions) {
        for (QuizQuestionDTO q : questions) {
            String type = normalizeType(q.getType());
            List<QuizOptionDTO> opts = q.getOptions();
            if (opts == null || opts.isEmpty()) throw new IllegalArgumentException("Question must have options");
            long correctCount = opts.stream().filter(o -> Boolean.TRUE.equals(o.getCorrect())).count();
            if ("SINGLE".equals(type)) {
                if (correctCount != 1) throw new IllegalArgumentException("SINGLE question must have exactly 1 correct option");
            } else if ("MULTI".equals(type)) {
                if (correctCount < 2) throw new IllegalArgumentException("MULTI question must have at least 2 correct options");
            } else {
                throw new IllegalArgumentException("Invalid question type: " + q.getType());
            }
        }
    }

    private String normalizeType(String t) {
        if (t == null) throw new IllegalArgumentException("Question type required");
        String s = t.trim().toUpperCase();
        if (!s.equals("SINGLE") && !s.equals("MULTI")) throw new IllegalArgumentException("Type must be SINGLE or MULTI");
        return s;
    }

    private int scoreQuestion(QuizQuestionDTO q, Set<Long> selected) {
        List<QuizOptionDTO> opts = q.getOptions();
        Set<Long> correctIds = opts.stream().filter(o -> Boolean.TRUE.equals(o.getCorrect())).map(QuizOptionDTO::getId).collect(Collectors.toSet());
        Set<Long> wrongSelected = selected.stream().filter(id -> opts.stream().anyMatch(o -> o.getId().equals(id) && !Boolean.TRUE.equals(o.getCorrect()))).collect(Collectors.toSet());
        int points = q.getPoints() == null ? 0 : q.getPoints();

        if ("SINGLE".equals(normalizeType(q.getType()))) {
            if (wrongSelected.size() > 0) return 0;
            if (selected.size() != 1) return 0;
            Long chosen = selected.iterator().next();
            return correctIds.contains(chosen) ? points : 0;
        } else {
            if (wrongSelected.size() > 0) return 0;
            int totalCorrect = correctIds.size();
            int selectedCorrect = (int) selected.stream().filter(correctIds::contains).count();
            int threshold = (totalCorrect / 2) + 1; // IT HAS TO BE MORE THAN HALF.....
            if (selectedCorrect < threshold) return 0;
            BigDecimal frac = new BigDecimal(selectedCorrect).divide(new BigDecimal(totalCorrect), 8, RoundingMode.HALF_UP);
            BigDecimal raw = frac.multiply(new BigDecimal(points));
            return raw.setScale(0, RoundingMode.HALF_UP).intValue();
        }
    }
}
