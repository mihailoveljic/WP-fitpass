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

import beans.models.TrainingType;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ICRUDService;

@Path ("TrainingTypeController")
public class TrainingTypeController implements ICRUDController<TrainingType, TrainingType> {

	@Context
	ServletContext ctx;
	
	public TrainingTypeController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initTrainingTypeService(ctx);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<TrainingType> getAll(){
		ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>)ctx.getAttribute("TrainingTypeService");
		return trainingTypeService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public TrainingType get(@PathParam("id") long id) {
		ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
		return trainingTypeService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public TrainingType create(TrainingType trainingType) {
		ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
		return trainingTypeService.create(trainingType);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(TrainingType trainingType) {
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
