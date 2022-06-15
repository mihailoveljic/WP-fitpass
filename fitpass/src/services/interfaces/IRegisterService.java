package services.interfaces;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;

public interface IRegisterService {

	public Buyer registerBuyer(Buyer buyer, ICRUDService<Buyer> service);
	public Coach registerCoach(Coach coach, ICRUDService<Coach> service);
	public Manager registerManager(Manager manager, ICRUDService<Manager> service);
	public Administrator registerAdministrator(Administrator administrator, ICRUDService<Administrator> service);
}
