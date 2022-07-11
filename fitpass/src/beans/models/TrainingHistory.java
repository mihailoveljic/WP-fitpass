package beans.models;

import java.io.Serializable;
import java.util.Date;

public class TrainingHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 347514008638487485L;
	private long id;
	private Date dateTime;
	private long trainingId;
	private long buyerId;
	private long coachId;
	private boolean isDeleted;
	
	public TrainingHistory() {}

	public TrainingHistory(long id, Date dateTime, long trainingId, long buyerId, long coachId, boolean isDeleted) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.trainingId = trainingId;
		this.buyerId = buyerId;
		this.coachId = coachId;
		this.isDeleted = isDeleted;
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
	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
