package beans.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Training implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7998463339413836605L;
	private long id;
	private String name;
	private TrainingType trainingType;
	private SportsFacility sportsFacility;
	private int duration;
	private Coach coach;
	private String description;
	private BufferedImage image;
	
	public Training() {}

	public Training(long id, String name, TrainingType trainingType, SportsFacility sportsFacility, int duration, Coach coach,
			String description) throws IOException {
		super();
		this.id = id;
		this.name = name;
		this.trainingType = trainingType;
		this.sportsFacility = sportsFacility;
		this.duration = duration;
		this.coach = coach;
		this.description = description;
		this.image = ImageIO.read(new File(name + ".png2"));
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

	public SportsFacility getSportsFacility() {
		return sportsFacility;
	}

	public void setSportsFacility(SportsFacility sportsFacility) {
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

	public BufferedImage getImage() {
		return image;
	}
	
}
