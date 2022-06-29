package services.implementations;

import java.util.ArrayList;
import java.util.Collection;

import beans.models.Manager;
import daos.interfaces.IDAO;
import services.interfaces.IManagerService;

public class ManagerService implements IManagerService {

	private IDAO<Manager> managerDAO;
	
	
	
	public ManagerService(IDAO<Manager> managerDAO) {
		super();
		this.managerDAO = managerDAO;
	}

	@Override
	public Collection<Manager> getAll() {
		return managerDAO.getAll();
	}

	@Override
	public Manager get(long id) {
		return managerDAO.get(String.valueOf(id));
	}

	@Override
	public Manager create(Manager manager) {
		return managerDAO.create(manager);
	}

	@Override
	public boolean update(Manager manager) {
		return managerDAO.update(manager);
	}

	@Override
	public boolean delete(long id) {
		return managerDAO.delete(String.valueOf(id));
	}

	@Override
	public Manager getByUsername(String username) {
		Collection<Manager> managers = managerDAO.getAll();
		for(Manager m : managers) {
			if(m.getUsername().compareTo(username) == 0) {
				return m;
			}
		}
		return null;
	}
	@Override
	public Collection<Manager> getFree() {
		 Collection<Manager> managers = new ArrayList<Manager>(managerDAO.getAll());
		 managers.removeIf(m -> m.getSportsFacilityId() != -1);
		 return managers;
	}

}
