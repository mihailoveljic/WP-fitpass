package beans.models;

public class Coach extends User {
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
