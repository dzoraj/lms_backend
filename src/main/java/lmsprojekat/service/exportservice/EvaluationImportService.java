package lmsprojekat.service.exportservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lmsprojekat.dto.exportdto.ImportResult;
import lmsprojekat.export.xml.EvaluationXml;
import lmsprojekat.service.exportservice.EvaluationPersistorJpa.ImportMode;

@Service
public class EvaluationImportService {

    private final XmlValidationService validator;
    private final EvaluationPersistorJpa persistor;
    private final XmlMapper xmlMapper;

    public EvaluationImportService(XmlValidationService validator, EvaluationPersistorJpa persistor) {
        this.validator = validator;
        this.persistor = persistor;
        this.xmlMapper = new XmlMapper();
        this.xmlMapper.findAndRegisterModules();
    }

    public void validateXml(String xml) {
        validator.validate(xml);
    }

    public EvaluationXml parse(String xml) {
        try {
            return xmlMapper.readValue(xml, EvaluationXml.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid XML structure: " + e.getMessage(), e);
        }
    }

    @Transactional
    public ImportResult importXml(String xml, ImportMode mode) {
        validateXml(xml);
        EvaluationXml payload = parse(xml);
        if (payload.getId() == null) {
            throw new IllegalArgumentException("Evaluation id is required");
        }
        if (payload.getAttempts() == null || payload.getAttempts().isEmpty()) {
            return new ImportResult(payload.getId(), 0, 0, 0, 0, mode == ImportMode.REPLACE);
        }
        return persistor.persist(payload, mode);
    }
}
