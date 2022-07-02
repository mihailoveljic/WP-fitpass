package services.implementations;

import java.util.Collection;

import beans.models.TrainingHistory;
import daos.interfaces.IDAO;
import services.interfaces.ITrainingHistoryService;

public class TrainingHistoryService implements ITrainingHistoryService {

	private IDAO<TrainingHistory> trainingHistoryDAO;
	
	
	public TrainingHistoryService(IDAO<TrainingHistory> trainingHistoryDAO) {
		super();
		this.trainingHistoryDAO = trainingHistoryDAO;
	}

	@Override
	public Collection<TrainingHistory> getAll() {
		return trainingHistoryDAO.getAll();
	}

	@Override
	public TrainingHistory get(long id) {
		return trainingHistoryDAO.get(String.valueOf(id));
	}

	@Override
	public TrainingHistory create(TrainingHistory trainingHistory) {
		return trainingHistoryDAO.create(trainingHistory);
	}

	@Override
	public boolean update(TrainingHistory trainingHistory) {
		return trainingHistoryDAO.update(trainingHistory);
	}

	@Override
	public boolean delete(long id) {
		return trainingHistoryDAO.delete(String.valueOf(id));
	}

}
