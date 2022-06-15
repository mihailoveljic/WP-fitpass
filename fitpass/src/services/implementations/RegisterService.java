package services.implementations;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import services.interfaces.IRegisterService;

public class RegisterService implements IRegisterService {

	private LoginService loginService;
	private BuyerService buyerService;
	private CoachService coachService;
	private ManagerService managerService;
	private AdministratorService administratorService;
	
	
	public RegisterService(LoginService loginService, BuyerService buyerService, CoachService coachService,
			ManagerService managerService, AdministratorService administratorService) {
		super();
		this.loginService = loginService;
		this.buyerService = buyerService;
		this.coachService = coachService;
		this.managerService = managerService;
		this.administratorService = administratorService;
	}

	public RegisterService() {}

	@Override
	public boolean registerBuyer(Buyer buyer) {
		return false;
	}

	@Override
	public boolean registerCoach(Coach coach) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerManager(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerAdministrator(Administrator administrator) {
		// TODO Auto-generated method stub
		return false;
	}

}
