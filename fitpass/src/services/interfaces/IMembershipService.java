package services.interfaces;

import java.util.ArrayList;

import beans.models.Membership;

public interface IMembershipService extends ICRUDService<Membership>{
	public Membership getByBuyer(long id);

	public ArrayList<Long> getAllMembershipNumbers();
}
