package services.implementations;

import javax.servlet.ServletContext;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import daos.implementations.AdministratorDAO;
import daos.implementations.BuyerDAO;
import daos.implementations.CoachDAO;
import daos.implementations.ManagerDAO;
import daos.interfaces.IDAO;
import repositories.implementations.AdministratorRepository;
import repositories.implementations.BuyerRepository;
import repositories.implementations.CoachRepository;
import repositories.implementations.ManagerRepository;
import repositories.interfaces.IRepository;

public class ContextInitService {
	
	public static void initAdministratorService(ServletContext ctx) {
		if (ctx.getAttribute("AdministratorService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Administrator> buyerRepository = new AdministratorRepository(contextPath);
	    	
	    	IDAO<Administrator> administratorDAO = new AdministratorDAO(buyerRepository);
	    	
			ctx.setAttribute("AdministratorService", new AdministratorService(administratorDAO));
		}
	}
	
	public static void initBuyerService(ServletContext ctx) {
		if (ctx.getAttribute("BuyerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Buyer> buyerRepository = new BuyerRepository(contextPath);
	    	
	    	IDAO<Buyer> buyerDAO = new BuyerDAO(buyerRepository);
	    	
			ctx.setAttribute("BuyerService", new BuyerService(buyerDAO));
		}
	}
	
	public static void initCoachService(ServletContext ctx) {
		if (ctx.getAttribute("CoachService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Coach> coachRepository = new CoachRepository(contextPath);
	    	
	    	IDAO<Coach> coachDAO = new CoachDAO(coachRepository);
	    	
			ctx.setAttribute("CoachService", new CoachService(coachDAO));
		}
	}

	public static void initManagerService(ServletContext ctx) {
		if (ctx.getAttribute("ManagerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Manager> managerRepository = new ManagerRepository(contextPath);
	    	
	    	IDAO<Manager> managerDAO = new ManagerDAO(managerRepository);
	    	
			ctx.setAttribute("ManagerService", new ManagerService(managerDAO));
		}
	}
	
	public static void initRegisterService(ServletContext ctx) {
		if (ctx.getAttribute("RegisterService") == null) {
			ctx.setAttribute("RegisterService", new RegisterService());
		}
	}

	public static void initLoginService(ServletContext ctx) {
		if (ctx.getAttribute("LoginService") == null) {
			ctx.setAttribute("LoginService", new LoginService());
		}
	}

}
