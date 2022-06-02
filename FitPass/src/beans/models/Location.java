package beans.models;

public class Location {
	private double id;
	private double latitude;
	private double longitude;
	private Address address;
	
	public Location() {}

	public Location(double id, double latitude, double longitude, Address address) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
