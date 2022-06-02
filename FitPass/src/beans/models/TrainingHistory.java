package beans.models;

import java.sql.Date;

public class TrainingHistory {
	private double id;
	private Date dateTime;
	private Training training;
	private Buyer buyer;
	private Coach coach;
	
	public TrainingHistory() {}

	public TrainingHistory(double id, Date dateTime, Training training, Buyer buyer, Coach coach) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.training = training;
		this.buyer = buyer;
		this.coach = coach;
	}
	
	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	
}
