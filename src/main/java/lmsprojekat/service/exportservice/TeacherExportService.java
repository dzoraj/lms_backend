package lmsprojekat.service.exportservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lmsprojekat.export.xml.AddressXml;
import lmsprojekat.export.xml.TeacherXml;
import lmsprojekat.export.xml.TeachersXml;
import lmsprojekat.export.xml.TitleXml;
import lmsprojekat.repository.export.TeacherExportRepository;
import lmsprojekat.repository.export.TeacherExportRow;
import lmsprojekat.repository.export.TeacherTitleRow;

@Service
public class TeacherExportService {

	private final TeacherExportRepository repo;

	public TeacherExportService(TeacherExportRepository repo) {
		this.repo = repo;
	}

	public List<TeacherXml> buildTeachers() {
		return buildTeachers(null);
	}

	public List<TeacherXml> buildTeachers(Long teacherId) {
		List<TeacherExportRow> rows = (teacherId == null) ? repo.findAllTeachersFlat()
				: repo.findTeacherFlatById(teacherId);

		Map<Long, TeacherXml> teacherMap = new LinkedHashMap<>();
		for (TeacherExportRow r : rows) {
			AddressXml addr = new AddressXml(r.getStreet(), r.getNumber(), r.getCity(), r.getCountry());
			TeacherXml tx = new TeacherXml(r.getId(), r.getName(), r.getJmbg(), r.getEmail(), r.getBiography(), addr);
			teacherMap.put(r.getId(), tx);
		}

		if (teacherMap.isEmpty()) {
			return new ArrayList<>();
		}

		List<Long> ids = new ArrayList<>(teacherMap.keySet());
		List<TeacherTitleRow> titleRows = repo.findTitlesForTeachers(ids);

		Map<Long, Map<Long, TitleXml>> perTeacherTitles = new HashMap<>();

		for (TeacherTitleRow tr : titleRows) {
			Long tId = tr.getTeacherId();
			Long titleId = tr.getTitleId();
			if (!teacherMap.containsKey(tId))
				continue;

			Map<Long, TitleXml> titlesForTeacher = perTeacherTitles.computeIfAbsent(tId, k -> new LinkedHashMap<>());

			TitleXml titleXml = titlesForTeacher.get(titleId);
			if (titleXml == null) {
				titleXml = new TitleXml(titleId, tr.getSelectionDate(), tr.getEndDate());
				titlesForTeacher.put(titleId, titleXml);
			}
			titleXml.addType(tr.getTypeName());
		}
 
		for (Map.Entry<Long, Map<Long, TitleXml>> e : perTeacherTitles.entrySet()) {
			TeacherXml teacher = teacherMap.get(e.getKey());
			if (teacher != null) {
				teacher.setTitles(new ArrayList<>(e.getValue().values()));
			}
		}

		return new ArrayList<>(teacherMap.values());
	}

	public String toXml(List<TeacherXml> teachers) throws Exception {
		XmlMapper xml = new XmlMapper();
		xml.enable(SerializationFeature.INDENT_OUTPUT);
		TeachersXml root = new TeachersXml(teachers);
		return xml.writeValueAsString(root);
	}
}
