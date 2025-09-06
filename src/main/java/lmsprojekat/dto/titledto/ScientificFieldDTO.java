package lmsprojekat.dto.titledto;

public class ScientificFieldDTO {

    private Long id;
    private String name;
    private TitleDTO title;

    public ScientificFieldDTO() {
    }

    public ScientificFieldDTO(Long id, String name, TitleDTO title) {
        this.id = id;
        this.name = name;
        this.title = title;
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

    public TitleDTO getTitle() {
        return title;
    }

    public void setTitle(TitleDTO title) {
        this.title = title;
    }
}
