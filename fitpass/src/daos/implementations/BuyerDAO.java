package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.Buyer;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class BuyerDAO implements IDAO<Buyer> {

	private Map<String, Buyer> buyers = new HashMap<String, Buyer>();
	private IRepository<Buyer> buyerRepository;
	
	public BuyerDAO(IRepository<Buyer> buyerRepository) {
		super();
		this.buyerRepository = buyerRepository;
		buyers = buyerRepository.load();
	}

	@Override
	public Collection<Buyer> getAll() {
		buyers.values().removeIf(buyer -> (buyer.getIsDeleted()));
		return buyers.values();
	}

	@Override
	public Buyer get(String id) {
		return buyers.containsKey(id) ? buyers.get(id) : null;
	}

	@Override
	public Buyer create(Buyer buyer) {
		long maxId = 0;
		for (String id : buyers.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		buyer.setId(maxId);
		buyers.put(String.valueOf(buyer.getId()), buyer);
		buyerRepository.save(buyers);
		return buyer;
	}

	@Override
	public boolean update(Buyer buyer) {
		if(buyers.put(String.valueOf(buyer.getId()), buyer) != null) {
			buyerRepository.save(buyers);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(buyers.containsKey(id)) {
			buyers.get(id).setIsDeleted(true);
			buyerRepository.save(buyers);
			return true;
		}
		return false;
	}

}
