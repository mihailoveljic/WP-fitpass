package services.implementations;

import java.util.Collection;

import beans.models.BuyerType;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class BuyerTypeService implements ICRUDService<BuyerType> {

private IDAO<BuyerType> buyerTypeDAO;
	
	
	
	public BuyerTypeService(IDAO<BuyerType> buyerTypeDAO) {
		super();
		this.buyerTypeDAO = buyerTypeDAO;
	}

	@Override
	public Collection<BuyerType> getAll() {
		return buyerTypeDAO.getAll();
	}

	@Override
	public BuyerType get(long id) {
		return buyerTypeDAO.get(String.valueOf(id));
	}

	@Override
	public BuyerType create(BuyerType buyerType) {
		return buyerTypeDAO.create(buyerType);
	}

	@Override
	public boolean update(BuyerType buyerType) {
		return buyerTypeDAO.update(buyerType);
	}

	@Override
	public boolean delete(long id) {
		return buyerTypeDAO.delete(String.valueOf(id));
	}

}
