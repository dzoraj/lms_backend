package lmsprojekat.dto.titledto;

public class TitleTypeDTO {

    private Long id;
    private String name;
    private TitleDTO title;

    public TitleTypeDTO() {
    }

    public TitleTypeDTO(Long id, String name, TitleDTO title) {
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
