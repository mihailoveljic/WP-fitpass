package beans.models;

import java.io.Serializable;
import java.time.LocalDate;

public class TrainingHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 347514008638487485L;
	private long id;
	private LocalDate dateTime;
	private long trainingId;
	private long buyerId;
	private long coachId;
	
	public TrainingHistory() {}

	public TrainingHistory(long id, LocalDate dateTime, long trainingId, long buyerId, long coachId) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.trainingId = trainingId;
		this.buyerId = buyerId;
		this.coachId = coachId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getCoachId() {
		return coachId;
	}

	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}

	
}
