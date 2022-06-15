package beans.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SportsFacility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6725392041839697593L;
	private long id;
	private String name;
	private SportsFacilityType sportsFacilityType;
	private ArrayList<ContentOfTheFacility> contentsOfTheFacility;
	private boolean openStatus;
	private Location location;
	private String image;
	private double averageRating;
	private WorkingHours workingHours;
	
	public SportsFacility() {}

	public SportsFacility(long id ,String name, SportsFacilityType sportsFacilityType, ArrayList<ContentOfTheFacility> contentsOfTheFacility,
			boolean openStatus, Location location, String image, double averageRating,
			WorkingHours workingHours) {
		super();
		this.name = name;
		this.sportsFacilityType = sportsFacilityType;
		this.contentsOfTheFacility = contentsOfTheFacility;
		this.openStatus = openStatus;
		this.location = location;
		this.image = image;
		this.averageRating = averageRating;
		this.workingHours = workingHours;
		this.id = id;
	}
	
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

	public SportsFacilityType getSportsFacilityType() {
		return sportsFacilityType;
	}

	public void setSportsFacilityType(SportsFacilityType sportsFacilityType) {
		this.sportsFacilityType = sportsFacilityType;
	}

	public ArrayList<ContentOfTheFacility> getContentsOfTheFacility() {
		return contentsOfTheFacility;
	}

	public void setContentsOfTheFacility(ArrayList<ContentOfTheFacility> contentsOfTheFacility) {
		this.contentsOfTheFacility = contentsOfTheFacility;
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
		this.image=image;
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
