package services.interfaces;

import beans.models.Buyer;

public interface IBuyerService extends ICRUDService<Buyer> {

	public Buyer getByUsername(String username);
	
}
