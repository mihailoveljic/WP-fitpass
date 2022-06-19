package services.interfaces;

import beans.dtos.UserRegistrationDTO;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;

public interface IRegisterService {

	public Buyer registerBuyer(UserRegistrationDTO userRegistrationDTO, ICRUDService<Buyer> service);
	public Coach registerCoach(UserRegistrationDTO userRegistrationDTO, ICRUDService<Coach> service);
	public Manager registerManager(UserRegistrationDTO userRegistrationDTO, ICRUDService<Manager> service);
}
