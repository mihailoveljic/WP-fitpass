package controllers.implementations;

import java.util.Collection;

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

import beans.models.Coach;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ICoachService;

@Path("/coaches")
public class CoachController implements ICRUDController<Coach> {


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
	@Override
	public Collection<Coach> getAll(){
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Coach get(@PathParam("id") long id) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Coach create(Coach coach) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.create(coach);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Coach coach) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.update(coach);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		return coachService.delete(id);
	}
}
