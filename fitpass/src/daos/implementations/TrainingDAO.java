package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.Training;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class TrainingDAO implements IDAO<Training>{

	private Map<String, Training> trainings = new HashMap<String, Training>();
	private IRepository<Training> trainingRepository;
	
	public TrainingDAO(IRepository<Training> trainingRepository) {
		super();
		this.trainingRepository = trainingRepository;
		trainings = trainingRepository.load();
	}

	@Override
	public Collection<Training> getAll() {
		return trainings.values();
	}

	@Override
	public Training get(String id) {
		return trainings.containsKey(id) ? trainings.get(id) : null;
	}

	@Override
	public Training create(Training training) {
		long maxId = 0;
		for (String id : trainings.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		training.setId(maxId);
		trainings.put(String.valueOf(training.getId()), training);
		trainingRepository.save(trainings);
		return training;
	}

	@Override
	public boolean update(Training training) {
		if(trainings.put(String.valueOf(training.getId()), training) != null) {
			trainingRepository.save(trainings);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(trainings.remove(id) != null) {
			trainingRepository.save(trainings);
			return true;
		}
		return false;
	}

}
