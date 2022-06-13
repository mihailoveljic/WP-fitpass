package daos.implementations;

import java.util.Collection;
import java.util.HashMap;

import beans.models.Buyer;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class BuyerDAO implements IDAO<Buyer> {

	private HashMap<Long, Buyer> buyers = new HashMap<Long, Buyer>();
	private IRepository<Buyer> buyerRepository;
	
	public BuyerDAO(IRepository<Buyer> buyerRepository) {
		super();
		this.buyerRepository = buyerRepository;
		buyers = buyerRepository.load();
	}

	@Override
	public Collection<Buyer> getAll() {
		return buyers.values();
	}

	@Override
	public Buyer get(long id) {
		return buyers.containsKey(id) ? buyers.get(id) : null;
	}

	@Override
	public Buyer create(Buyer buyer) {
		long maxId = -1;
		for (long id : buyers.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		
		buyer.setId(maxId);
		buyers.put(buyer.getId(), buyer);
		buyerRepository.save(buyers);
		return buyer;
	}

	@Override
	public boolean update(Buyer buyer) {
		if(buyers.put(buyer.getId(), buyer) != null) {
			buyerRepository.save(buyers);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(long id) {
		if(buyers.remove(id) != null) {
			buyerRepository.save(buyers);
			return true;
		}
		return false;
	}

}
