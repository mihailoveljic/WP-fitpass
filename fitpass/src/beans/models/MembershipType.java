package beans.models;

import java.io.Serializable;

public class MembershipType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7891954538698337794L;
	private long id;
	private String name;
	private int numberOfDailyTrainings;
	private String image;
	private int durationInDays;
	private double price;
	private boolean isDeleted;
	
	public MembershipType() {}

	public MembershipType(long id, String name, int numberOfDailyTraining, String image, int durationInDays, double price ,boolean isDeleted) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfDailyTrainings = numberOfDailyTraining;
		this.image = image;
		this.durationInDays = durationInDays;
		this.price = price;
		this.isDeleted = isDeleted;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public boolean getIsDeleted() {
		return isDeleted;
	}
	public int getNumberOfDailyTrainings() {
		return numberOfDailyTrainings;
	}

	public void setNumberOfDailyTrainings(int numberOfDailyTrainings) {
		this.numberOfDailyTrainings = numberOfDailyTrainings;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
