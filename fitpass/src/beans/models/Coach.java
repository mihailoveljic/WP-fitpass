package beans.models;

import java.io.Serializable;
import java.util.ArrayList;


public class Coach extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4143166464460969899L;
	private ArrayList<Long> trainingHistoryIds;
	
	public Coach() {}

	public ArrayList<Long> getTrainingHistoryIds() {
		return trainingHistoryIds;
	}

	public void setTrainingHistoryIds(ArrayList<Long> trainingHistoryIds) {
		this.trainingHistoryIds = trainingHistoryIds;
	}
}
