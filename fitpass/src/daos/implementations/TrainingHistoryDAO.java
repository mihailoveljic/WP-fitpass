package daos.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.TrainingHistory;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class TrainingHistoryDAO implements IDAO<TrainingHistory> {

	private Map<String, TrainingHistory> trainingHistory = new HashMap<String, TrainingHistory>();
	private IRepository<TrainingHistory> trainingHistoryRepository;
	
	public TrainingHistoryDAO(IRepository<TrainingHistory> trainingHistoryRepository) {
		super();
		this.trainingHistoryRepository = trainingHistoryRepository;
		trainingHistory = trainingHistoryRepository.load();
	}

	@Override
	public Collection<TrainingHistory> getAll() {
		Collection<TrainingHistory> retVal = new ArrayList<TrainingHistory>(trainingHistory.values());
		retVal.removeIf(x -> (x.getIsDeleted()));
		return retVal;
	}

	@Override
	public TrainingHistory get(String id) {
		if(trainingHistory.containsKey(id)) {
			if(trainingHistory.get(id).getIsDeleted() == false){
				return trainingHistory.get(id);
			}
		}
		return null;	
	}

	@Override
	public TrainingHistory create(TrainingHistory th) {
		long maxId = 0;
		for (String id : trainingHistory.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		th.setId(maxId);
		trainingHistory.put(String.valueOf(th.getId()), th);
		trainingHistoryRepository.save(trainingHistory);
		return th;
	}

	@Override
	public boolean update(TrainingHistory th) {
		if(trainingHistory.put(String.valueOf(th.getId()), th) != null) {
			trainingHistoryRepository.save(trainingHistory);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(trainingHistory.containsKey(id)) {
			trainingHistory.get(id).setIsDeleted(true);
			trainingHistoryRepository.save(trainingHistory);
			return true;
		}
		return false;
	}

}
