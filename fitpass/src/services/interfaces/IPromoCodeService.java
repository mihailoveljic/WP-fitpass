package services.interfaces;

import beans.models.PromoCode;

public interface IPromoCodeService extends ICRUDService<PromoCode> {
	public PromoCode checkIfPromoCodeExists(String mark);
}
