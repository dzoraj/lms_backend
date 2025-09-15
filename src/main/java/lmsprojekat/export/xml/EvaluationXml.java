package lmsprojekat.export.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "evaluationExport")
public class EvaluationXml {

	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private Long id;

	@JacksonXmlProperty(localName = "subjectName")
	private String subjectName;

	@JacksonXmlProperty(localName = "courseRealizationId")
	private Long courseRealizationId;

	@JacksonXmlProperty(localName = "evaluationType")
	private String evaluationType;

	@JacksonXmlProperty(localName = "instrument")
	private String instrument;

	@JacksonXmlProperty(localName = "maxPoints")
	private Integer maxPoints;

	@JacksonXmlProperty(localName = "startTime")
	private String startTime;

	@JacksonXmlProperty(localName = "endTime")
	private String endTime;

	@JacksonXmlElementWrapper(localName = "attempts")
	@JacksonXmlProperty(localName = "attempt")
	private List<EvaluationAttemptXml> attempts = new ArrayList<>();

	public EvaluationXml() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getCourseRealizationId() {
		return courseRealizationId;
	}

	public void setCourseRealizationId(Long courseRealizationId) {
		this.courseRealizationId = courseRealizationId;
	}

	public String getEvaluationType() {
		return evaluationType;
	}

	public void setEvaluationType(String evaluationType) {
		this.evaluationType = evaluationType;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public Integer getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(Integer maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<EvaluationAttemptXml> getAttempts() {
		return attempts;
	}

	public void setAttempts(List<EvaluationAttemptXml> attempts) {
		this.attempts = attempts;
	}
}
