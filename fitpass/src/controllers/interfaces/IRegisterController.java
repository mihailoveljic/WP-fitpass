package controllers.interfaces;

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

public interface IRegisterController {

	@POST
	@Path("/registerBuyer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerBuyer(@Context HttpServletRequest request, Buyer buyer);
	
	@POST
	@Path("/registerCoach")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerCoach(@Context HttpServletRequest request, Coach coach);
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerManager(@Context HttpServletRequest request, Manager manager);
	
	@POST
	@Path("/registerAdministrator")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registerAdministrator(@Context HttpServletRequest request, Administrator administrator);
}
