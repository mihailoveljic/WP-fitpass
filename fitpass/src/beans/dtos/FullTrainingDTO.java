package beans.dtos;

import beans.models.Coach;

public class FullTrainingDTO {
	private long id;
	private String name;
	private String trainingType;
	private SportsFacilityDTO sportsFacility;
	private int duration;
	private Coach coach;
	private String description;
	private String image;
	private double additionalPrice;
	
	public FullTrainingDTO() {}
	
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
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	public SportsFacilityDTO getSportsFacility() {
		return sportsFacility;
	}
	public void setSportsFacility(SportsFacilityDTO sportsFacility) {
		this.sportsFacility = sportsFacility;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getAdditionalPrice() {
		return additionalPrice;
	}
	public void setAdditionalPrice(double additionalPrice) {
		this.additionalPrice = additionalPrice;
	}
	
	
}
