package services.implementations;

import java.util.Collection;

import beans.models.PromoCode;
import daos.interfaces.IDAO;
import services.interfaces.IPromoCodeService;

public class PromoCodeService implements IPromoCodeService {

	private IDAO<PromoCode> promoCodeDAO;
	
	
	
	public PromoCodeService(IDAO<PromoCode> promoCodeDAO) {
		super();
		this.promoCodeDAO = promoCodeDAO;
	}

	@Override
	public Collection<PromoCode> getAll() {
		return promoCodeDAO.getAll();
	}

	@Override
	public PromoCode get(long id) {
		return promoCodeDAO.get(String.valueOf(id));
	}

	@Override
	public PromoCode create(PromoCode promoCode) {
		return promoCodeDAO.create(promoCode);
	}

	@Override
	public boolean update(PromoCode promoCode) {
		return promoCodeDAO.update(promoCode);
	}

	@Override
	public boolean delete(long id) {
		return promoCodeDAO.delete(String.valueOf(id));
	}
}
