package controllers.implementations;

import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.dtos.UserUpdateDTO;
import beans.models.Coach;
import services.implementations.ContextInitService;
import services.interfaces.ICoachService;

@Path("/coaches")
public class CoachController {


	@Context
	ServletContext ctx;
	
	public CoachController() {}


	@PostConstruct
	public void init() {
		ContextInitService.initCoachService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coach> getAll(){
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Coach get(@PathParam("id") long id) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Coach create(Coach coach) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.create(coach);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(UserUpdateDTO userUpdateDTO) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		Coach coach = coachService.get(userUpdateDTO.getId());
		if(!userUpdateDTO.getOldPassword().equals(coach.getPassword())) return false;
		
		if(userUpdateDTO.isChangePassword()) {
			coach.setPassword(userUpdateDTO.getNewPassword());
		}
		coach.setName(userUpdateDTO.getName());
		coach.setSurname(userUpdateDTO.getSurname());
		coach.setGender(userUpdateDTO.getGender());
		try {
			coach.setDateOfBirth(new Date(
					userUpdateDTO.getDateOfBirth().getYear(),
					userUpdateDTO.getDateOfBirth().getMonth(),
					userUpdateDTO.getDateOfBirth().getDay()));
		} catch (Exception e) {return false;}
		
		return coachService.update(coach);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") long id) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.delete(id);
	}
}
