package beans.models;

import java.io.Serializable;
import java.sql.Date;

public class TrainingHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 347514008638487485L;
	private long id;
	private Date dateTime;
	private Training training;
	private Buyer buyer;
	private Coach coach;
	
	public TrainingHistory() {}

	public TrainingHistory(long id, Date dateTime, Training training, Buyer buyer, Coach coach) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.training = training;
		this.buyer = buyer;
		this.coach = coach;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
