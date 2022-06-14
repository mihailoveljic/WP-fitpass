package services.implementations;

import java.util.Collection;

import beans.models.Buyer;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class BuyerService implements ICRUDService<Buyer> {

	private IDAO<Buyer> buyerDAO;
	
	
	
	public BuyerService(IDAO<Buyer> buyerDAO) {
		super();
		this.buyerDAO = buyerDAO;
	}

	@Override
	public Collection<Buyer> getAll() {
		return buyerDAO.getAll();
	}

	@Override
	public Buyer get(long id) {
		return null;
	}

	@Override
	public Buyer create(Buyer buyer) {
		return buyerDAO.create(buyer);
	}

	@Override
	public boolean update(Buyer t) {
		return false;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

}
