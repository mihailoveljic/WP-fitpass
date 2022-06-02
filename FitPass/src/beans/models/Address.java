package beans.models;

public class Address {
	private double id;
	private String street;
	private String number;
	private String city;
	private int zipCode;
	
	public Address() {}

	public Address(double id, String street, String number, String city, int zipCode) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
		this.zipCode = zipCode;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
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
