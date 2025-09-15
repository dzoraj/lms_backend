package lmsprojekat.repository.export;

public interface EvalHeader {
	Long getId();

	String getStartTime();

	String getEndTime();

	Integer getMaxPoints();

	String getEvaluationType();

	String getInstrument();

	Long getCourseRealizationId();

	String getSubjectName();
}
