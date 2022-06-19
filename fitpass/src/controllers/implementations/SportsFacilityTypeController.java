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

import beans.models.SportsFacilityType;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ICRUDService;

@Path ("SportsFacilityTypeController")
public class SportsFacilityTypeController implements ICRUDController<SportsFacilityType, SportsFacilityType> {

	@Context
	ServletContext ctx;
	
	public SportsFacilityTypeController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initSportsFacilityTypeService(ctx);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<SportsFacilityType> getAll(){
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>)ctx.getAttribute("SportsFacilityTypeService");
		return sportsFacilityTypeService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public SportsFacilityType get(@PathParam("id") long id) {
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>) ctx.getAttribute("SportsFacilityTypeService");
		return sportsFacilityTypeService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public SportsFacilityType create(SportsFacilityType sportsFacilityType) {
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>) ctx.getAttribute("SportsFacilityTypeService");
		return sportsFacilityTypeService.create(sportsFacilityType);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(SportsFacilityType sportsFacilityType) {
		return false;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		return false;
	}

}
