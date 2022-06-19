package beans.dtos;

import java.util.ArrayList;

import beans.models.Location;
import beans.models.WorkingHours;

public class SportsFacilityDTO {

	private long id;
	private String name;
	private String sportsFacilityType;
	private ArrayList<String> facilityContents;
	private boolean openStatus;
	private Location location;
	private String image;
	private double averageRating;
	private WorkingHours workingHours;
	
	public SportsFacilityDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSportsFacilityType() {
		return sportsFacilityType;
	}

	public void setSportsFacilityType(String sportsFacilityType) {
		this.sportsFacilityType = sportsFacilityType;
	}

	public ArrayList<String> getFacilityContents() {
		return facilityContents;
	}

	public void setFacilityContents(ArrayList<String> facilityContents) {
		this.facilityContents = facilityContents;
	}

	public boolean isOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(boolean openStatus) {
		this.openStatus = openStatus;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public WorkingHours getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(WorkingHours workingHours) {
		this.workingHours = workingHours;
	}
	
	
}
