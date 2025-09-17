package lmsprojekat.dto.quizdto;

import java.util.List;

public class QuizSubmissionDTO {
	private Long knowledgeEvaluationId;
	private Long studentInYearId;
	private List<SubmittedAnswerDTO> answers;

	public static class SubmittedAnswerDTO {
		private Long questionId;
		private List<Long> selectedOptionIds;

		public SubmittedAnswerDTO() {
		}

		public SubmittedAnswerDTO(Long questionId, List<Long> selectedOptionIds) {
			this.questionId = questionId;
			this.selectedOptionIds = selectedOptionIds;
		}

		public Long getQuestionId() {
			return questionId;
		}

		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
		}

		public List<Long> getSelectedOptionIds() {
			return selectedOptionIds;
		}

		public void setSelectedOptionIds(List<Long> selectedOptionIds) {
			this.selectedOptionIds = selectedOptionIds;
		}
	}

	public QuizSubmissionDTO() {
	}

	public QuizSubmissionDTO(Long knowledgeEvaluationId, Long studentInYearId, List<SubmittedAnswerDTO> answers) {
		this.knowledgeEvaluationId = knowledgeEvaluationId;
		this.studentInYearId = studentInYearId;
		this.answers = answers;
	}

	public Long getKnowledgeEvaluationId() {
		return knowledgeEvaluationId;
	}

	public void setKnowledgeEvaluationId(Long knowledgeEvaluationId) {
		this.knowledgeEvaluationId = knowledgeEvaluationId;
	}

	public Long getStudentInYearId() {
		return studentInYearId;
	}

	public void setStudentInYearId(Long studentInYearId) {
		this.studentInYearId = studentInYearId;
	}

	public List<SubmittedAnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<SubmittedAnswerDTO> answers) {
		this.answers = answers;
	}
}
