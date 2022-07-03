package beans.dtos;

import java.util.Date;

import beans.models.Coach;

public class TrainingHistoryDTO {
	private long id;
	private Date date;
	private FullTrainingDTO training;
	private BuyerDTO buyer;
	private Coach coach;
	
	public TrainingHistoryDTO() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public FullTrainingDTO getTraining() {
		return training;
	}

	public void setTraining(FullTrainingDTO training) {
		this.training = training;
	}

	public BuyerDTO getBuyer() {
		return buyer;
	}

	public void setBuyer(BuyerDTO buyer) {
		this.buyer = buyer;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	
}
