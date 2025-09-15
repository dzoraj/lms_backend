package lmsprojekat.export.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AttemptStudentXml {
	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private Long id;

	@JacksonXmlProperty(localName = "name")
	private String name;

	@JacksonXmlProperty(localName = "email")
	private String email;

	@JacksonXmlProperty(localName = "indexNumber")
	private String indexNumber;

	public AttemptStudentXml() {
	}

	public AttemptStudentXml(Long id, String name, String email, String indexNumber) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.indexNumber = indexNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

}
