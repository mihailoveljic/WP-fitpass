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

import beans.models.TrainingHistory;
import services.implementations.ContextInitService;
import services.interfaces.ITrainingHistoryService;

@Path("/TrainingHistoryController")
public class TrainingHistoryController {


	@Context
	ServletContext ctx;
	
	public TrainingHistoryController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initTrainingHistoryService(ctx);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingHistory> getAll(){
		ITrainingHistoryService trainingHistoryService = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
		
		return trainingHistoryService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TrainingHistory get(@PathParam("id") long id) {
		ITrainingHistoryService trainingHistoryService = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
		
		return trainingHistoryService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TrainingHistory create(TrainingHistory trainingHistory) {
		ITrainingHistoryService trainingHistoryService = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
		
		return trainingHistoryService.create(trainingHistory);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(TrainingHistory trainingHistory) {
		ITrainingHistoryService trainingHistoryService = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
		
		return trainingHistoryService.update(trainingHistory);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") long id) {
		ITrainingHistoryService trainingHistoryService = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
		
		return trainingHistoryService.delete(id);
	}
}
