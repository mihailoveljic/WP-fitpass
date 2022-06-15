package services.implementations;

import java.util.Collection;

import beans.models.Manager;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class ManagerService implements ICRUDService<Manager> {

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

}
