package services.interfaces;

import beans.models.Manager;

public interface IManagerService extends ICRUDService<Manager> {

	public Manager getByUsername(String username);
}
