package lmsprojekat.export.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TeacherXml {
	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private Long id;

	@JacksonXmlProperty(localName = "name")
	private String name;

	@JacksonXmlProperty(localName = "jmbg")
	private String jmbg;

	@JacksonXmlProperty(localName = "email")
	private String email;

	@JacksonXmlProperty(localName = "biography")
	private String biography;

	@JacksonXmlProperty(localName = "address")
	private AddressXml address;

	@JacksonXmlElementWrapper(localName = "titles")
	@JacksonXmlProperty(localName = "title")
	private List<TitleXml> titles = new ArrayList<>();

	public TeacherXml() {
	}

	public TeacherXml(Long id, String name, String jmbg, String email, String biography, AddressXml address) {
		this.id = id;
		this.name = name;
		this.jmbg = jmbg;
		this.email = email;
		this.biography = biography;
		this.address = address;
	}

	public void addTitle(TitleXml t) {
		this.titles.add(t);
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public AddressXml getAddress() {
		return address;
	}

	public void setAddress(AddressXml address) {
		this.address = address;
	}

	public List<TitleXml> getTitles() {
		return titles;
	}

	public void setTitles(List<TitleXml> titles) {
		this.titles = titles;
	}
}
