package beans.models;

import java.io.Serializable;
import java.util.ArrayList;


public class Coach extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4143166464460969899L;
	private ArrayList<TrainingHistory> trainingHistory;
	
	public Coach() {}

	public ArrayList<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}

	
	
}
