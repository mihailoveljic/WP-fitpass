package services.implementations;

import java.util.Collection;
import java.util.Date;

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

	@Override
	public PromoCode checkIfPromoCodeExists(String mark) {
		Collection<PromoCode> promoCodes = promoCodeDAO.getAll();
		for(PromoCode pc : promoCodes) {
			if(pc.getMark().compareTo(mark)==0) {
				return pc;
			}
		}
		return null;
	}

	@Override
	public boolean isPromoCodeValid(PromoCode promocode) {
		promocode = this.checkIfPromoCodeExists(promocode.getMark());
		Date currentDate = new Date();
		if(promocode != null) {
			if(promocode.getExpirationDate().compareTo(currentDate) > 0) {
				if(promocode.getHowManyTimeCanBeUsed() > 0) {
					return true;
				}
			}
		}
		return false;
	}
}
