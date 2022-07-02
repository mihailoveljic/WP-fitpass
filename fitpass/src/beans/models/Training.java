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
	private boolean isDeleted;
	
	public Training() {}

	public Training(long id, String name, long trainingTypeId, long sportsFacilityId, int duration, long coachId, String image,
			String description, boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.trainingTypeId = trainingTypeId;
		this.sportsFacilityId = sportsFacilityId;
		this.duration = duration;
		this.coachId = coachId;
		this.description = description;
		this.image = image;
		this.isDeleted = isDeleted;
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
	public boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
