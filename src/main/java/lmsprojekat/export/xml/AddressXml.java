package lmsprojekat.export.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AddressXml {
	@JacksonXmlProperty(localName = "street")
	private String street;
	@JacksonXmlProperty(localName = "number")
	private String number;
	@JacksonXmlProperty(localName = "city")
	private String city;
	@JacksonXmlProperty(localName = "country")
	private String country;



	public AddressXml() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressXml(String street, String number, String city, String country) {
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
