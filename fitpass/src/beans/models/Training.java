package beans.models;

import java.io.Serializable;


public class Training implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7998463339413836605L;
	private long id;
	private String name;
	private long trainingTypeId;
	private long sportsFacilityId;
	private int duration;
	private long coachId;
	private String description;
	private String image;
	private double additionalPrice;
	
	public Training() {}

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

	public long getTrainingTypeId() {
		return trainingTypeId;
	}

	public void setTrainingTypeId(long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
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

	public long getCoachId() {
		return coachId;
	}

	public void setCoachId(long coachId) {
		this.coachId = coachId;
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

	public double isAdditionalPrice() {
		return additionalPrice;
	}

	public void setAdditionalPrice(double additionalPrice) {
		this.additionalPrice = additionalPrice;
	}

	
}
