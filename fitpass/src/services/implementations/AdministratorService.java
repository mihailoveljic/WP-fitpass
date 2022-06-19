package services.implementations;

import java.util.Collection;

import beans.models.Administrator;
import daos.interfaces.IDAO;
import services.interfaces.IAdministratorService;

public class AdministratorService implements IAdministratorService {

	private IDAO<Administrator> administratorDAO;
	
	
	
	public AdministratorService(IDAO<Administrator> administratorDAO) {
		super();
		this.administratorDAO = administratorDAO;
	}

	@Override
	public Collection<Administrator> getAll() {
		return administratorDAO.getAll();
	}

	@Override
	public Administrator get(long id) {
		return administratorDAO.get(String.valueOf(id));
	}

	@Override
	public Administrator create(Administrator administrator) {
		return administratorDAO.create(administrator);
	}

	@Override
	public boolean update(Administrator administrator) {
		return administratorDAO.update(administrator);
	}

	@Override
	public boolean delete(long id) {
		return administratorDAO.delete(String.valueOf(id));
	}

	@Override
	public Administrator getByUsername(String username) {
		Collection<Administrator> administrators = administratorDAO.getAll();
		for(Administrator a : administrators) {
			if(a.getUsername().compareTo(username) == 0) {
				return a;
			}
		}
		return null;
	}

}
