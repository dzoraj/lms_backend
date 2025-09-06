package lmsprojekat.dto.teachingdto;

import java.time.LocalDateTime;
import java.util.List;

public class TeachingMaterialDTO {

    private Long id;
    private String name;
    private String authors;
    private LocalDateTime yearOfPublication;

    private Long learningOutcomeId;

    private List<Long> fileIds;

    public TeachingMaterialDTO() {}

    public TeachingMaterialDTO(Long id, String name, String authors, LocalDateTime yearOfPublication,
                               Long learningOutcomeId, List<Long> fileIds) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.yearOfPublication = yearOfPublication;
        this.learningOutcomeId = learningOutcomeId;
        this.fileIds = fileIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }

    public LocalDateTime getYearOfPublication() { return yearOfPublication; }
    public void setYearOfPublication(LocalDateTime yearOfPublication) { this.yearOfPublication = yearOfPublication; }

    public Long getLearningOutcomeId() { return learningOutcomeId; }
    public void setLearningOutcomeId(Long learningOutcomeId) { this.learningOutcomeId = learningOutcomeId; }

    public List<Long> getFileIds() { return fileIds; }
    public void setFileIds(List<Long> fileIds) { this.fileIds = fileIds; }
}
