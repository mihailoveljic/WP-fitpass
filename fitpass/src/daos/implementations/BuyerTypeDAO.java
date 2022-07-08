package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.BuyerType;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class BuyerTypeDAO implements IDAO<BuyerType>{
	
	private Map<String, BuyerType> buyerTypes = new HashMap<String, BuyerType>();
	private IRepository<BuyerType> buyerTypeRepository;
	
	public BuyerTypeDAO(IRepository<BuyerType> buyerTypeRepository) {
		super();
		this.buyerTypeRepository = buyerTypeRepository;
		buyerTypes = buyerTypeRepository.load();
	}

	@Override
	public Collection<BuyerType> getAll() {
		buyerTypes.values().removeIf(bt -> (bt.getIsDeleted()));
		return buyerTypes.values();
	}

	@Override
	public BuyerType get(String id) {
		return buyerTypes.containsKey(id) ? buyerTypes.get(id) : null;
	}

	@Override
	public BuyerType create(BuyerType buyerType) {
		long maxId = 0;
		for (String id : buyerTypes.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		buyerType.setId(maxId);
		buyerTypes.put(String.valueOf(buyerType.getId()), buyerType);
		buyerTypeRepository.save(buyerTypes);
		return buyerType;
	}

	@Override
	public boolean update(BuyerType buyerType) {
		if(buyerTypes.put(String.valueOf(buyerType.getId()), buyerType) != null) {
			buyerTypeRepository.save(buyerTypes);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(buyerTypes.containsKey(id)) {
			buyerTypes.get(id).setIsDeleted(true);
			buyerTypeRepository.save(buyerTypes);
			return true;
		}
		return false;
	}

}
