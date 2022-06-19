package services.implementations;

import java.util.Collection;

import beans.models.Coach;
import daos.interfaces.IDAO;
import services.interfaces.ICoachService;

public class CoachService implements ICoachService {

	private IDAO<Coach> coachDAO;
	
	public CoachService(IDAO<Coach> coachDAO) {
		super();
		this.coachDAO = coachDAO;
	}

	@Override
	public Collection<Coach> getAll() {
		return coachDAO.getAll();
	}

	@Override
	public Coach get(long id) {
		return coachDAO.get(String.valueOf(id));
	}

	@Override
	public Coach create(Coach coach) {
		return coachDAO.create(coach);
	}

	@Override
	public boolean update(Coach coach) {
		return coachDAO.update(coach);
	}

	@Override
	public boolean delete(long id) {
		return coachDAO.delete(String.valueOf(id));
	}

	@Override
	public Coach getByUsername(String username) {
		Collection<Coach> coaches = coachDAO.getAll();
		for(Coach c : coaches) {
			if(c.getUsername().compareTo(username) == 0) {
				return c;
			}
		}
		return null;
	}

}
