package lmsprojekat.export.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class StudentXml {

	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private Long id;

	@JacksonXmlProperty(localName = "name")
	private String name;

	@JacksonXmlProperty(localName = "jmbg")
	private String jmbg;

	@JacksonXmlProperty(localName = "email")
	private String email;

	@JacksonXmlProperty(localName = "address")
	private AddressXml address;

	public StudentXml() {
	}

	public StudentXml(Long id, String name, String jmbg, String email, AddressXml address) {
		this.id = id;
		this.name = name;
		this.jmbg = jmbg;
		this.email = email;
		this.address = address;
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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressXml getAddress() {
		return address;
	}

	public void setAddress(AddressXml address) {
		this.address = address;
	}
}
