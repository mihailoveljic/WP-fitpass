package services.interfaces;

import java.util.Collection;

import beans.models.Manager;

public interface IManagerService extends ICRUDService<Manager> {

	public Manager getByUsername(String username);
	public Collection<Manager> getFree();
}
