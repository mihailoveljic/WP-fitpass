package controllers.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.dtos.UserRegistrationDTO;
import beans.dtos.UserToken;

public interface IRegisterController {

	@POST
	@Path("/registerBuyer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken registerBuyer(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO);
	
	@POST
	@Path("/registerCoach")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken registerCoach(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO);
	
	@POST
	@Path("/registerManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken registerManager(@Context HttpServletRequest request, UserRegistrationDTO userRegistrationDTO);
}
