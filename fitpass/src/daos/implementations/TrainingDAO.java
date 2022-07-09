package daos.implementations;

import java.util.ArrayList;
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
		Collection<Training> retVal = new ArrayList<Training>(trainings.values());
		retVal.removeIf(x -> (x.getIsDeleted()));
		return retVal;
	}

	@Override
	public Training get(String id) {
		if(trainings.containsKey(id)) {
			if(trainings.get(id).getIsDeleted() == false){
				return trainings.get(id);
			}
		}
		return null;	
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
		if(trainings.containsKey(id)) {
			trainings.get(id).setIsDeleted(true);
			trainingRepository.save(trainings);
			return true;
		}
		return false;
	}

}
