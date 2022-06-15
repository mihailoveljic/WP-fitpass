package services.interfaces;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;

public interface IRegisterService {

	public boolean registerBuyer(Buyer buyer);
	public boolean registerCoach(Coach coach);
	public boolean registerManager(Manager manager);
	public boolean registerAdministrator(Administrator administrator);
}
