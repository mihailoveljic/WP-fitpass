package beans.dtos;

import beans.models.Coach;
import beans.models.TrainingType;

public class TrainingDTO {

	private long id;
	private String name;
	private TrainingType trainingType;
	private long sportsFacilityId;
	private int duration;
	private Coach coach;
	private String description;
	private double additionalPrice;
	private String image;
	
	public TrainingDTO() {}

	public TrainingDTO(long id, String name, TrainingType trainingType, long sportsFacilityId, int duration,
			Coach coach, String description, String image, double additionalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.trainingType = trainingType;
		this.sportsFacilityId = sportsFacilityId;
		this.duration = duration;
		this.coach = coach;
		this.description = description;
		this.image = image;
		this.additionalPrice = additionalPrice;
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

	public TrainingType getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public long getSportsFacilityId() {
		return sportsFacilityId;
	}

	public void setSportsFacilityId(long sportsFacilityId) {
		this.sportsFacilityId = sportsFacilityId;
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
