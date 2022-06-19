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

import beans.models.SportsFacility;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ISportsFacilityService;

@Path("/SportsFacilitiesController")
public class SportsFacilityController implements ICRUDController<SportsFacility> {

	@Context
	ServletContext ctx;
	
	public SportsFacilityController() {}


	@PostConstruct
	public void init() {
		ContextInitService.initSportsFacilityService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<SportsFacility> getAll(){
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		return sportsFacilityService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public SportsFacility get(@PathParam("id") long id) {
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService)ctx.getAttribute("SportsFacilityService");
		return sportsFacilityService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public SportsFacility create(SportsFacility sportsFacility) {
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		return sportsFacilityService.create(sportsFacility);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(SportsFacility sportsFacility) {
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		return sportsFacilityService.update(sportsFacility);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		return sportsFacilityService.delete(id);
	}
	

}
