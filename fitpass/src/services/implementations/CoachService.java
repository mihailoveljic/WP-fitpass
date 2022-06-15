package services.implementations;

import java.util.Collection;

import beans.models.Coach;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class CoachService implements ICRUDService<Coach> {

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

}
