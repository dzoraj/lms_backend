package lmsprojekat.dto.teachingdto;

import java.util.List;
import lmsprojekat.model.File;

public class EvaluationInstrumentDTO {

    private Long id;
    private String name;
    private List<KnowledgeEvaluationDTO> evaluations;
    private File file;

    public EvaluationInstrumentDTO() {}

    public EvaluationInstrumentDTO(Long id, String name, List<KnowledgeEvaluationDTO> evaluations, File file) {
        this.id = id;
        this.name = name;
        this.evaluations = evaluations;
        this.file = file;
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

    public List<KnowledgeEvaluationDTO> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<KnowledgeEvaluationDTO> evaluations) {
        this.evaluations = evaluations;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
