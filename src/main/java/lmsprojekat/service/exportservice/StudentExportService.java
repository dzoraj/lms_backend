package lmsprojekat.service.exportservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lmsprojekat.export.xml.AddressXml;
import lmsprojekat.export.xml.StudentXml;
import lmsprojekat.export.xml.StudentsXml;
import lmsprojekat.repository.export.StudentExportRepository;
import lmsprojekat.repository.export.StudentExportRow;

@Service
public class StudentExportService {

	private final StudentExportRepository repo;

	public StudentExportService(StudentExportRepository repo) {
		this.repo = repo;
	}

	public List<StudentXml> buildStudents() {
		List<StudentExportRow> rows = repo.findAllStudentsFlat();
		return map(rows);
	}

	public List<StudentXml> buildStudents(Long id) {
		List<StudentExportRow> rows = repo.findStudentFlatById(id);
		return map(rows);
	}

	private List<StudentXml> map(List<StudentExportRow> rows) {
		List<StudentXml> out = new ArrayList<>();
		for (StudentExportRow r : rows) {
			AddressXml addr = new AddressXml(r.getStreet(), r.getNumber(), r.getCity(), r.getCountry());
			out.add(new StudentXml(r.getId(), r.getName(), r.getJmbg(), r.getEmail(), addr));
		}
		return out;
	}

	public String toXml(List<StudentXml> students) throws Exception {
		XmlMapper xml = new XmlMapper();
		xml.enable(SerializationFeature.INDENT_OUTPUT);
		StudentsXml root = new StudentsXml(students);
		return xml.writeValueAsString(root);
	}
}
