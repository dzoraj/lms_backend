package lmsprojekat.dto.subjectdto;

import java.util.List;

import lmsprojekat.dto.teachingdto.TeachingMaterialDTO;

public class LearningOutcomeFullDTO {
    private Long id;
    private String description;
    private List<TeachingMaterialDTO> teachingMaterials;
	public LearningOutcomeFullDTO(Long id, String description, List<TeachingMaterialDTO> teachingMaterials) {
		super();
		this.id = id;
		this.description = description;
		this.teachingMaterials = teachingMaterials;
	}
	public LearningOutcomeFullDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<TeachingMaterialDTO> getTeachingMaterials() {
		return teachingMaterials;
	}
	public void setTeachingMaterials(List<TeachingMaterialDTO> teachingMaterials) {
		this.teachingMaterials = teachingMaterials;
	}
    
    
}
