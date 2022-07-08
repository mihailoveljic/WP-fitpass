package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.Coach;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class CoachDAO implements IDAO<Coach> {

	private Map<String, Coach> coaches = new HashMap<String, Coach>();
	private IRepository<Coach> coachRepository;
	
	public CoachDAO(IRepository<Coach> coachRepository) {
		super();
		this.coachRepository = coachRepository;
		coaches = coachRepository.load();
	}

	@Override
	public Collection<Coach> getAll() {
		coaches.values().removeIf(coach -> (coach.getIsDeleted()));
		return coaches.values();
	}

	@Override
	public Coach get(String id) {
		return coaches.containsKey(id) ? coaches.get(id) : null;
	}

	@Override
	public Coach create(Coach coach) {
		long maxId = 0;
		for (String id : coaches.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		coach.setId(maxId);
		coaches.put(String.valueOf(coach.getId()), coach);
		coachRepository.save(coaches);
		return coach;
	}

	@Override
	public boolean update(Coach coach) {
		if(coaches.put(String.valueOf(coach.getId()), coach) != null) {
			coachRepository.save(coaches);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(coaches.containsKey(id)) {
			coaches.get(id).setIsDeleted(true);
			coachRepository.save(coaches);
			return true;
		}
		return false;
	}

}
