package lmsprojekat.export.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "teachersExport")
public class TeachersXml {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "teacher")
	private List<TeacherXml> teachers = new ArrayList<>();

	public TeachersXml() {
	}

	public TeachersXml(List<TeacherXml> teachers) {
		this.teachers = teachers;
	}

	public List<TeacherXml> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherXml> teachers) {
		this.teachers = teachers;
	}
}
