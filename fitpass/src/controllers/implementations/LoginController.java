package controllers.implementations;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.models.Buyer;
import beans.models.User;
import beans.dtos.UserLoginDTO;
import controllers.interfaces.ILoginController;
import daos.implementations.BuyerDAO;
import daos.interfaces.IDAO;
import repositories.implementations.BuyerRepository;
import repositories.interfaces.IRepository;
import services.implementations.BuyerService;
import services.implementations.LoginService;
import services.interfaces.ICRUDService;
import services.interfaces.ILoginService;

@Path("/LoginController")
public class LoginController implements ILoginController {
	
	@Context
	ServletContext ctx;
	
	public LoginController() {}


	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("BuyerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Buyer> buyerRepository = new BuyerRepository(contextPath);
	    	
	    	IDAO<Buyer> buyerDAO = new BuyerDAO(buyerRepository);
	    	
			ctx.setAttribute("BuyerService", new BuyerService(buyerDAO));
		}
		if (ctx.getAttribute("LoginService") == null) {
			ctx.setAttribute("LoginService", new LoginService());
		}
	}
	

	@Override
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean logout(@Context HttpServletRequest request) {
		ILoginService loginService = (ILoginService) ctx.getAttribute("LoginService");
		return loginService.logout(request);
	}
	
	
	@Override
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User login(@Context HttpServletRequest request, UserLoginDTO userLoginDTO) {
		User retVal = null;
		ICRUDService<Buyer> buyerService = (ICRUDService<Buyer>) ctx.getAttribute("BuyerService");
		ILoginService loginService = (ILoginService) ctx.getAttribute("LoginService");
		retVal = (User)request.getSession().getAttribute("user");
		if (retVal == null) {
			Collection<Buyer> buyers = buyerService.getAll();
			Collection<User> users = new ArrayList<User>();
			for(Buyer b: buyers) {
				users.add(b);
			}
			User user = loginService.login(userLoginDTO, users);
			request.getSession().setAttribute("user", user);
			retVal = user;
		}
		return retVal;
	}

	
}
