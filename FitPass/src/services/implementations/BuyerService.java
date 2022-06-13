package services.implementations;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import javax.servlet.ServletContext;
import beans.models.Buyer;
import daos.implementations.BuyerDAO;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

@Path("/buyers")
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Buyer create(Buyer buyer) {
		// TODO Auto-generated method stub
		return buyerDAO.create(buyer);
	}

	@Override
	public boolean update(Buyer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
