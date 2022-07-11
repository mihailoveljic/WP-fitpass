package services.interfaces;

import java.util.Collection;

import beans.models.TrainingHistory;

public interface ITrainingHistoryService extends ICRUDService<TrainingHistory> {

	Collection<TrainingHistory> GetForBuyerInLast30Days(long id);
	Collection<TrainingHistory> getAllTrainingsHistoryByCertainCoach(long id);
	Collection<TrainingHistory> getAllPersonalTrainingsHistoryByCertainCoach(long id, ITrainingService trainingService);
	Collection<TrainingHistory> getAllGroupTrainingsHistoryByCertainCoach(long id, ITrainingService trainingService);
	Collection<TrainingHistory> getAllByTrainingId(long id);
	Collection<TrainingHistory> getAllByBuyer(long id);
}
