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

import beans.dtos.UserRegistrationDTO;
import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import controllers.interfaces.IRegisterController;
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
import services.implementations.AdministratorService;
import services.implementations.BuyerService;
import services.implementations.CoachService;
import services.implementations.LoginService;
import services.implementations.ManagerService;
import services.implementations.RegisterService;
import services.interfaces.IAdministratorService;
import services.interfaces.IBuyerService;
import services.interfaces.ICoachService;
import services.interfaces.IManagerService;
import services.interfaces.IRegisterService;

@Path("/RegisterController")
public class RegisterController implements IRegisterController {
	@Context
	ServletContext ctx;
	
	public RegisterController() {}


	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom

		if (ctx.getAttribute("BuyerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Buyer> buyerRepository = new BuyerRepository(contextPath);
	    	
	    	IDAO<Buyer> buyerDAO = new BuyerDAO(buyerRepository);
	    	
			ctx.setAttribute("BuyerService", new BuyerService(buyerDAO));
		}
		if (ctx.getAttribute("CoachService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Coach> coachRepository = new CoachRepository(contextPath);
	    	
	    	IDAO<Coach> coachDAO = new CoachDAO(coachRepository);
	    	
			ctx.setAttribute("CoachService", new CoachService(coachDAO));
		}
		if (ctx.getAttribute("ManagerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Manager> managerRepository = new ManagerRepository(contextPath);
	    	
	    	IDAO<Manager> managerDAO = new ManagerDAO(managerRepository);
	    	
			ctx.setAttribute("ManagerService", new ManagerService(managerDAO));
		}
		if (ctx.getAttribute("AdministratorService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Administrator> administratorRepository = new AdministratorRepository(contextPath);
	    	
	    	IDAO<Administrator> administratorDAO = new AdministratorDAO(administratorRepository);
	    	
			ctx.setAttribute("AdministratorService", new AdministratorService(administratorDAO));
		}
		if (ctx.getAttribute("LoginService") == null) {
			ctx.setAttribute("LoginService", new LoginService());
		}
		if (ctx.getAttribute("RegisterService") == null) {
			ctx.setAttribute("RegisterService", new RegisterService());
		}
	}

	@POST
	@Path("/registerBuyer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerBuyer(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		if(buyerService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		if(coachService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		if(managerService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		if(administratorService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		Buyer buyer2 = registerService.registerBuyer(userRegistrationDTO, buyerService);
		if(buyer2 == null) {
			return false;
		} else return true;
	}
	
	@POST
	@Path("/registerCoach")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerCoach(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		return false;
	}
	
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerManager(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		if(buyerService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		if(coachService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		if(managerService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		if(administratorService.getByUsername(userRegistrationDTO.getUsername())!=null) return false;
		Manager manager2 = registerService.registerManager(userRegistrationDTO, managerService);
		if(manager2 == null) {
			return false;
		} else return true;
	}

}
