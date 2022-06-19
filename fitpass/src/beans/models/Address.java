package beans.models;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6430069054174416845L;
	private String country; 
	private String street;
	private String number;
	private String city;
	private int zipCode;
	
	public Address() {}

	public Address(String country, String street, String number, String city, int zipCode) {
		super();
		this.country=country;
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	
	
}
