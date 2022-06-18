package controllers.interfaces;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.dtos.UserLoginDTO;
import beans.dtos.UserToken;;

public interface ILoginController {

	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean logout(@Context HttpServletRequest request);
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserToken  login(@Context HttpServletRequest request, UserLoginDTO userLoginDTO);
}
