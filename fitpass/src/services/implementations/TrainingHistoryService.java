package services.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import beans.models.TrainingHistory;
import daos.interfaces.IDAO;
import services.interfaces.ITrainingHistoryService;

public class TrainingHistoryService implements ITrainingHistoryService {

	private IDAO<TrainingHistory> trainingHistoryDAO;
	private static long DAY_IN_MILISECONDS = 86400000;
	
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

	@Override
	public Collection<TrainingHistory> GetForBuyerInLast30Days(long id) {
		
		Date now = new Date(System.currentTimeMillis() + DAY_IN_MILISECONDS * 30);
		
		Collection<TrainingHistory> trainingHistory = new ArrayList<TrainingHistory>(trainingHistoryDAO.getAll());
		trainingHistory.removeIf(training ->(training.getBuyerId() != id || training.getDateTime().compareTo(now) < 0));
		
		return trainingHistory;
	}

}
