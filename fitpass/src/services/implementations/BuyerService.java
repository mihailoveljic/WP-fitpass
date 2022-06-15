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
		return buyerDAO.get(String.valueOf(id));
	}

	@Override
	public Buyer create(Buyer buyer) {
		return buyerDAO.create(buyer);
	}

	@Override
	public boolean update(Buyer buyer) {
		return buyerDAO.update(buyer);
	}

	@Override
	public boolean delete(long id) {
		return buyerDAO.delete(String.valueOf(id));
	}

}
