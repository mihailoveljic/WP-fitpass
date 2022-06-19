package services.interfaces;

import beans.models.Coach;

public interface ICoachService extends ICRUDService<Coach> {
	
	public Coach getByUsername(String username);

}
