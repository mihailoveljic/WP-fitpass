package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import beans.models.PromoCode;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class PromoCodeDAO implements IDAO<PromoCode> {

	private Map<String, PromoCode> promoCodes = new HashMap<String, PromoCode>();
	private IRepository<PromoCode> promoCodeRepository;
	
	public PromoCodeDAO(IRepository<PromoCode> promoCodeRepository) {
		super();
		this.promoCodeRepository = promoCodeRepository;
		promoCodes = promoCodeRepository.load();
	}

	@Override
	public Collection<PromoCode> getAll() {
		return promoCodes.values();
	}

	@Override
	public PromoCode get(String id) {
		return promoCodes.containsKey(id) ? promoCodes.get(id) : null;
	}

	@Override
	public PromoCode create(PromoCode promoCode) {
		long maxId = 0;
		for (String id : promoCodes.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		promoCode.setId(maxId);
		promoCodes.put(String.valueOf(promoCode.getId()), promoCode);
		promoCodeRepository.save(promoCodes);
		return promoCode;
	}

	@Override
	public boolean update(PromoCode promoCode) {
		if(promoCodes.put(String.valueOf(promoCode.getId()), promoCode) != null) {
			promoCodeRepository.save(promoCodes);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(promoCodes.remove(id) != null) {
			promoCodeRepository.save(promoCodes);
			return true;
		}
		return false;
	}
}
