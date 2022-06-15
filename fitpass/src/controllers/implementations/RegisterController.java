package controllers.implementations;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import controllers.interfaces.IRegisterController;
import daos.implementations.BuyerDAO;
import daos.interfaces.IDAO;
import repositories.implementations.BuyerRepository;
import repositories.interfaces.IRepository;
import services.implementations.BuyerService;
import services.implementations.LoginService;
import services.implementations.RegisterService;

public class RegisterController implements IRegisterController {
	@Context
	ServletContext ctx;
	
	public RegisterController() {}


	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
//		if (ctx.getAttribute("BuyerService") == null) {
//	    	String contextPath = ctx.getRealPath("");
//	    	IRepository<Buyer> buyerRepository = new BuyerRepository(contextPath);
//	    	
//	    	IDAO<Buyer> buyerDAO = new BuyerDAO(buyerRepository);
//	    	
//			ctx.setAttribute("BuyerService", new BuyerService(buyerDAO));
//		}
//		if (ctx.getAttribute("CoachService") == null) {
//	    	String contextPath = ctx.getRealPath("");
//	    	IRepository<Coach> coachRepository = new CoachRepository(contextPath);
//	    	
//	    	IDAO<Coach> coachDAO = new CoachDAO(buyerRepository);
//	    	
//			ctx.setAttribute("CoachService", new CoachService(coachDAO));
//		}
//		if (ctx.getAttribute("ManagerService") == null) {
//	    	String contextPath = ctx.getRealPath("");
//	    	IRepository<Manager> managerRepository = new ManagerRepository(contextPath);
//	    	
//	    	IDAO<Manager> managerDAO = new ManagerDAO(managerRepository);
//	    	
//			ctx.setAttribute("ManagerService", new BuyerService(buyerDAO));
//		}
//		if (ctx.getAttribute("AdministratorService") == null) {
//	    	String contextPath = ctx.getRealPath("");
//	    	IRepository<Administrator> administratorRepository = new AdministratorRepository(contextPath);
//	    	
//	    	IDAO<Administrator> administratorDAO = new AdministratorDAO(administratorRepository);
//	    	
//			ctx.setAttribute("AdministratorService", new BuyerService(buyerDAO));
//		}
//		if (ctx.getAttribute("LoginService") == null) {
//			ctx.setAttribute("LoginService", new LoginService());
//		}
//		if (ctx.getAttribute("RegisterService") == null) {
//			ctx.setAttribute("RegisterService", new RegisterService(ctx.getAttribute("BuyerService"),
//					ctx.getAttribute("CoachService"),
//					ctx.getAttribute("BuyerService"),
//					ctx.getAttribute("ManagerService"),
//					ctx.getAttribute("AdministratorService")));
//		}
	}

	@POST
	@Path("/registerBuyer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerBuyer(@Context HttpServletRequest request, Buyer buyer) {
		return false;
	}
	
	@POST
	@Path("/registerCoach")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerCoach(@Context HttpServletRequest request, Coach coach) {
		return false;
	}
	
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerManager(@Context HttpServletRequest request, Manager manager) {
		return false;
	}
	
	@POST
	@Path("/registerAdministrator")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerAdministrator(@Context HttpServletRequest request, Administrator administrator) {
		return false;
	}

}
