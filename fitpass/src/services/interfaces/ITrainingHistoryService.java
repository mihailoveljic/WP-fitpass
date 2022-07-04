package services.interfaces;

import java.util.Collection;

import beans.models.TrainingHistory;

public interface ITrainingHistoryService extends ICRUDService<TrainingHistory> {

	Collection<TrainingHistory> GetForBuyerInLast30Days(long id);

	
}
