package services.interfaces;

import java.util.Collection;

import beans.models.Buyer;

public interface IBuyerService extends ICRUDService<Buyer> {

	public Buyer getByUsername(String username);
	public Collection<Buyer> getBuyersWhoVisitedCertainSportFacility(long id);
	
}
