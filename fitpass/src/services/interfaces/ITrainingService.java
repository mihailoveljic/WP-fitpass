package services.interfaces;

import java.util.Collection;

import beans.models.Training;

public interface ITrainingService extends ICRUDService<Training> {

	public Collection<Training> getAllTrainingsInCertainSportFacility(long id);

	public Collection<Training> getAllTrainingsForCoach(long id);
}
