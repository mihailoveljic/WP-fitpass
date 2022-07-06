package services.interfaces;

import beans.models.Membership;

public interface IMembershipService extends ICRUDService<Membership>{
	public Membership getByBuyer(long id);
}
