package controllers.implementations;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.dtos.UserLoginDTO;
import beans.dtos.UserRegistrationDTO;
import beans.dtos.UserToken;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import beans.models.User;
import controllers.interfaces.IRegisterController;
import services.implementations.ContextInitService;
import services.interfaces.IAdministratorService;
import services.interfaces.IBuyerService;
import services.interfaces.ICoachService;
import services.interfaces.ILoginService;
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

	@GET
	@Path("/checkIfUsernameIsUnique/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkIfUsernameIsUnique(@Context HttpServletRequest request, @PathParam("username") String username) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		ILoginService loginService = (ILoginService)ctx.getAttribute("LoginService");
		
		if(IsUsernameTaken(username, buyerService, coachService, managerService, administratorService)) return false;
		return true;
	}

	@POST
	@Path("/registerBuyer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken registerBuyer(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		ILoginService loginService = (ILoginService)ctx.getAttribute("LoginService");
		
		//if(IsUsernameTaken(userRegistrationDTO, buyerService, coachService, managerService, administratorService)) return null;
		
		Buyer buyer = registerService.registerBuyer(userRegistrationDTO, buyerService);
		
		if(buyer == null) {
			return null;
		} else {
			UserLoginDTO userLoginDTO = new UserLoginDTO();
			userLoginDTO.setUsername(buyer.getUsername());
			userLoginDTO.setPassword(buyer.getPassword());
			Collection<Buyer> buyers = buyerService.getAll();
			Collection<User> users = new ArrayList<User>();
			for(Buyer b: buyers) {
				users.add(b);
			}
			User user = loginService.login(userLoginDTO, users);
			UserToken userToken = new UserToken();
			userToken.setId(user.getId());
			userToken.setUsername(user.getUsername());
			userToken.setRole(user.getRole());
			request.getSession().setAttribute("userToken", userToken);
			return userToken;
		}
	}
	
	@POST
	@Path("/registerCoach")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken registerCoach(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		
//		if(IsUsernameTaken(userRegistrationDTO, buyerService, coachService, managerService, administratorService)) return null;
		
		Coach coach = registerService.registerCoach(userRegistrationDTO, coachService);
		
		if(coach == null) {
			return null;
		} else return null;
	}
	
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken registerManager(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO) {
		IRegisterService registerService = (IRegisterService) ctx.getAttribute("RegisterService");
		
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IManagerService managerService = (IManagerService)ctx.getAttribute("ManagerService");
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		
		//if(IsUsernameTaken(userRegistrationDTO, buyerService, coachService, managerService, administratorService)) return null;
		
		Manager manager = registerService.registerManager(userRegistrationDTO, managerService);
		if(manager == null) {
			return null;
		} else return null;
	}

	private boolean IsUsernameTaken(String username, IBuyerService buyerService,
			ICoachService coachService, IManagerService managerService, IAdministratorService administratorService) {
		
		if(buyerService.getByUsername(username) != null) return true;
		if(coachService.getByUsername(username) != null) return true;
		if(managerService.getByUsername(username) != null) return true;
		if(administratorService.getByUsername(username) != null) return true;
		return false;
	}

}
