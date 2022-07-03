package services.implementations;

import java.util.ArrayList;
import java.util.Collection;

import beans.models.Training;
import daos.interfaces.IDAO;
import services.interfaces.ITrainingService;

public class TrainingService implements ITrainingService {

	private IDAO<Training> trainingDAO;
	
	
	
	public TrainingService(IDAO<Training> trainingDAO) {
		super();
		this.trainingDAO = trainingDAO;
	}

	@Override
	public Collection<Training> getAll() {
		return trainingDAO.getAll();
	}

	@Override
	public Training get(long id) {
		return trainingDAO.get(String.valueOf(id));
	}

	@Override
	public Training create(Training training) {
		return trainingDAO.create(training);
	}

	@Override
	public boolean update(Training training) {
		return trainingDAO.update(training);
	}

	@Override
	public boolean delete(long id) {
		return trainingDAO.delete(String.valueOf(id));
	}

	@Override
	public Collection<Training> getAllTrainingsInCertainSportFacility(long id) {
		Collection<Training> trainings = new ArrayList<Training>(trainingDAO.getAll());
		trainings.removeIf(t -> (t.getSportsFacilityId() != id));
		
		return trainings;
	}

}
