package services.interfaces;

import beans.models.Administrator;

public interface IAdministratorService extends ICRUDService<Administrator> {
	
	public Administrator getByUsername(String username);
}
