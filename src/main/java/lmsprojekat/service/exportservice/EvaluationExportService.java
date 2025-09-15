package lmsprojekat.service.exportservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lmsprojekat.export.xml.AttemptStudentXml;
import lmsprojekat.export.xml.EvaluationAttemptXml;
import lmsprojekat.export.xml.EvaluationXml;
import lmsprojekat.repository.export.EvalAttemptRow;
import lmsprojekat.repository.export.EvalHeader;
import lmsprojekat.repository.export.EvaluationExportRepository;

@Service
public class EvaluationExportService {

    private final EvaluationExportRepository repo;

    public EvaluationExportService(EvaluationExportRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public EvaluationXml buildEvaluation(Long evaluationId) {
        EvalHeader h = repo.findEvaluationHeader(evaluationId);
        if (h == null) {
            throw new IllegalArgumentException("Evaluation not found: " + evaluationId);
        }

        EvaluationXml xml = new EvaluationXml();
        xml.setId(h.getId());
        xml.setSubjectName(h.getSubjectName());
        xml.setCourseRealizationId(h.getCourseRealizationId());
        xml.setEvaluationType(h.getEvaluationType());
        xml.setInstrument(h.getInstrument());
        xml.setMaxPoints(h.getMaxPoints());
        xml.setStartTime(h.getStartTime());
        xml.setEndTime(h.getEndTime());

        List<EvalAttemptRow> rows = repo.findAttempts(evaluationId);
        List<EvaluationAttemptXml> attempts = new ArrayList<>(rows.size());
        for (EvalAttemptRow r : rows) {
            AttemptStudentXml st = new AttemptStudentXml(
                r.getStudentId(),
                r.getStudentName(),
                r.getStudentEmail(),
                r.getIndexNumber()
            );
            attempts.add(new EvaluationAttemptXml(
                r.getAttemptId(),
                r.getPoints(),
                r.getLatest(),
                r.getNote(),
                st
            ));
        }
        xml.setAttempts(attempts);

        return xml;
    }

    public String toXml(EvaluationXml eval) throws Exception {
        XmlMapper xml = new XmlMapper();
        xml.enable(SerializationFeature.INDENT_OUTPUT);
        return xml.writeValueAsString(eval);
    }
}
