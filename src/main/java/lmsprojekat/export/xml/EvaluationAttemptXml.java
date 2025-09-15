package lmsprojekat.export.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class EvaluationAttemptXml {

	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private Long id;

	@JacksonXmlProperty(localName = "points")
	private Integer points;

	@JacksonXmlProperty(localName = "latest")
	private Boolean latest;

	@JacksonXmlProperty(localName = "note")
	private String note;

	@JacksonXmlProperty(localName = "student")
	private AttemptStudentXml student;

	public EvaluationAttemptXml() {
	}

	public EvaluationAttemptXml(Long id, Integer points, Boolean latest, String note, AttemptStudentXml student) {
		this.id = id;
		this.points = points;
		this.latest = latest;
		this.note = note;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Boolean getLatest() {
		return latest;
	}

	public void setLatest(Boolean latest) {
		this.latest = latest;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AttemptStudentXml getStudent() {
		return student;
	}

	public void setStudent(AttemptStudentXml student) {
		this.student = student;
	}

}
