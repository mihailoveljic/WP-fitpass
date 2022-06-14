package beans.models;

import java.io.Serializable;

public class Coach extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4143166464460969899L;
	private TrainingHistory trainingHistory;
	
	public Coach() {}

	public Coach(TrainingHistory trainingHistory) {
		super();
		this.trainingHistory = trainingHistory;
	}

	public TrainingHistory getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(TrainingHistory trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	
	
}
