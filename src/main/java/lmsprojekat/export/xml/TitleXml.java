package lmsprojekat.export.xml;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TitleXml {

	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private Long id;

	@JacksonXmlProperty(localName = "selectionDate")
	private String selectionDate;

	@JacksonXmlProperty(localName = "endDate")
	private String endDate;

	@JacksonXmlElementWrapper(localName = "types")
	@JacksonXmlProperty(localName = "type")
	private List<String> types = new ArrayList<>();



	public TitleXml() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TitleXml(Long id, String selectionDate, String endDate) {
		this.id = id;
		this.selectionDate = selectionDate;
		this.endDate = endDate;
	}

	public void addType(String typeName) {
		if (typeName != null && !typeName.isBlank()) {
			this.types.add(typeName);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSelectionDate() {
		return selectionDate;
	}

	public void setSelectionDate(String selectionDate) {
		this.selectionDate = selectionDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}
}
