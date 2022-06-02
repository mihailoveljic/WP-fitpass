package beans.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SportsFacility {
	private double id;
	private String name;
	private SportsFacilityType sportsFacilityType;
	private ContentOfTheFacility contentOfTheFacility;
	private boolean openStatus;
	private Location location;
	private BufferedImage image;
	private double averageRating;
	private WorkingHours workingHours;
	
	public SportsFacility() {}

	public SportsFacility(double id ,String name, SportsFacilityType sportsFacilityType, ContentOfTheFacility contentOfTheFacility,
			boolean openStatus, Location location, double averageRating,
			WorkingHours workingHours) throws IOException {
		super();
		this.name = name;
		this.sportsFacilityType = sportsFacilityType;
		this.contentOfTheFacility = contentOfTheFacility;
		this.openStatus = openStatus;
		this.location = location;
		this.image = ImageIO.read(new File(name + ".png"));
		this.averageRating = averageRating;
		this.workingHours = workingHours;
		this.id = id;
	}
	
	public double getId() {
		return id;
	}

	public void setId(double id) {
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

	public ContentOfTheFacility getContentOfTheFacility() {
		return contentOfTheFacility;
	}

	public void setContentOfTheFacility(ContentOfTheFacility contentOfTheFacility) {
		this.contentOfTheFacility = contentOfTheFacility;
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

	public BufferedImage getImage() {
		return image;
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
