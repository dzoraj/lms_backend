package lmsprojekat.repository.export;

public interface EvalAttemptRow {
	Long getAttemptId();

	Integer getPoints();

	Boolean getLatest();

	String getNote();

	Long getStudentInYearId();

	String getIndexNumber();

	Long getStudentId();

	String getStudentName();

	String getStudentEmail();
}
