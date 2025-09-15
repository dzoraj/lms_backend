package lmsprojekat.export.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "studentsExport")
public class StudentsXml {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "student")
	private List<StudentXml> students = new ArrayList<>();

	public StudentsXml() {
	}

	public StudentsXml(List<StudentXml> students) {
		this.students = students;
	}

	public List<StudentXml> getStudents() {
		return students;
	}

	public void setStudents(List<StudentXml> students) {
		this.students = students;
	}
}
