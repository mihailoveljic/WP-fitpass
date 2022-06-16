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
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import controllers.interfaces.IRegisterController;
import services.implementations.ContextInitService;
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
	public void init() {
		ContextInitService.initBuyerService(ctx);
		ContextInitService.initCoachService(ctx);
		ContextInitService.initManagerService(ctx);
		ContextInitService.initAdministratorService(ctx);
		ContextInitService.initLoginService(ctx);
		ContextInitService.initRegisterService(ctx);
	}

	@POST
	@Path("/registerBuyer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerBuyer(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		
		if(IsUsernameTaken(userRegistrationDTO, buyerService, coachService, managerService, administratorService)) return false;
		
		Buyer buyer = registerService.registerBuyer(userRegistrationDTO, buyerService);
		
		if(buyer == null) {
			return false;
		} else return true;
	}
	
	@POST
	@Path("/registerCoach")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerCoach(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		
		if(IsUsernameTaken(userRegistrationDTO, buyerService, coachService, managerService, administratorService)) return false;
		
		Coach coach = registerService.registerCoach(userRegistrationDTO, coachService);
		
		if(coach == null) {
			return false;
		} else return true;
	}
	
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerManager(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		
		if(IsUsernameTaken(userRegistrationDTO, buyerService, coachService, managerService, administratorService)) return false;
		
		Manager manager = registerService.registerManager(userRegistrationDTO, managerService);
		if(manager == null) {
			return false;
		} else return true;
	}

	private boolean IsUsernameTaken(UserRegistrationDTO userRegistrationDTO, IBuyerService buyerService,
			ICoachService coachService, IManagerService managerService, IAdministratorService administratorService) {
		
		if(buyerService.getByUsername(userRegistrationDTO.getUsername()) != null) return true;
		if(coachService.getByUsername(userRegistrationDTO.getUsername()) != null) return true;
		if(managerService.getByUsername(userRegistrationDTO.getUsername()) != null) return true;
		if(administratorService.getByUsername(userRegistrationDTO.getUsername()) != null) return true;
		return false;
	}

}
