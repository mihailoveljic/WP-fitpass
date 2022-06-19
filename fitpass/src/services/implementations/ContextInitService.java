package services.implementations;

import javax.servlet.ServletContext;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.FacilityContent;
import beans.models.Manager;
import beans.models.SportsFacility;
import daos.implementations.AdministratorDAO;
import daos.implementations.BuyerDAO;
import daos.implementations.CoachDAO;
import daos.implementations.FacilityContentDAO;
import daos.implementations.ManagerDAO;
import daos.implementations.SportsFacilityDAO;
import daos.interfaces.IDAO;
import repositories.implementations.AdministratorRepository;
import repositories.implementations.BuyerRepository;
import repositories.implementations.CoachRepository;
import repositories.implementations.FacilityContentRepository;
import repositories.implementations.ManagerRepository;
import repositories.implementations.SportsFacilityRepository;
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
	public static void initSportsFacilityService(ServletContext ctx) {
		if (ctx.getAttribute("SportsFacilityService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<SportsFacility> sportsFacilityRepository = new SportsFacilityRepository(contextPath);
	    	
	    	IDAO<SportsFacility> sportsFacilityDAO = new SportsFacilityDAO(sportsFacilityRepository);
	    	
			ctx.setAttribute("SportsFacilityService", new SportsFacilityService(sportsFacilityDAO));
		}
	}
	public static void initFacilityContentService(ServletContext ctx) {
		if (ctx.getAttribute("FacilityContentService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<FacilityContent> facilityContentRepository = new FacilityContentRepository(contextPath);
	    	
	    	IDAO<FacilityContent> sportsFacilityDAO = new FacilityContentDAO(facilityContentRepository);
	    	
			ctx.setAttribute("FacilityContentService", new FacilityContentService(sportsFacilityDAO));
		}
	}
	

}
