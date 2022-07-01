package controllers.implementations;

import java.util.ArrayList;
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

import beans.dtos.TrainingDTO;
import beans.models.Coach;
import beans.models.Training;
import beans.models.TrainingType;
import services.implementations.ContextInitService;
import services.interfaces.ICRUDService;
import services.interfaces.ICoachService;
import services.interfaces.ITrainingService;

@Path("/TrainingController")
public class TrainingController {

		@Context
		ServletContext ctx;
		
		public TrainingController() {}

		@PostConstruct
		public void init() {
			//ContextInitService.initBuyerService(ctx);
			//ContextInitService.initBuyerTypeService(ctx);
			//ContextInitService.initSportsFacilityService(ctx);
			ContextInitService.initTrainingService(ctx);
			ContextInitService.initTrainingTypeService(ctx);
			ContextInitService.initCoachService(ctx);
		}
		
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<TrainingDTO> getAll(){
			ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
			
			Collection<Training> trainings = trainingService.getAll();
			Collection<TrainingDTO> trainingDTOs = new ArrayList<TrainingDTO>();
			
			for(Training t : trainings) {
				TrainingDTO trainingDTO = new TrainingDTO();
				trainingDTO.setId(t.getId());
				trainingDTO.setName(t.getName());
				trainingDTO.setTrainingType(trainingTypeService.get(t.getTrainingTypeId()));
				trainingDTO.setSportsFacilityId(t.getSportsFacilityId());
				trainingDTO.setDuration(t.getDuration());
				trainingDTO.setCoach(coachService.get(t.getCoachId()));
				trainingDTO.setDescription(t.getDescription());
				trainingDTO.setImage(ctx.getContextPath() + "\\data\\img\\trainings\\" + t.getImage());
				trainingDTOs.add(trainingDTO);
			}
			return trainingDTOs;
		}
		@GET
		@Path("/getAllTrainingsInCertainSportFacility/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<TrainingDTO> getAllTrainingsInCertainSportFacility(@PathParam("id") long id) {
			ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
			
			Collection<Training> trainings = trainingService.getAllTrainingsInCertainSportFacility(id);
			Collection<TrainingDTO> trainingDTOs = new ArrayList<TrainingDTO>();
			
			for(Training t : trainings) {
				TrainingDTO trainingDTO = new TrainingDTO();
				trainingDTO.setId(t.getId());
				trainingDTO.setName(t.getName());
				trainingDTO.setTrainingType(trainingTypeService.get(t.getTrainingTypeId()));
				trainingDTO.setSportsFacilityId(t.getSportsFacilityId());
				trainingDTO.setDuration(t.getDuration());
				trainingDTO.setCoach(coachService.get(t.getCoachId()));
				trainingDTO.setDescription(t.getDescription());
				trainingDTO.setImage(ctx.getContextPath() + "\\data\\img\\trainings\\" + t.getImage());
				trainingDTOs.add(trainingDTO);
			}
			return trainingDTOs;
		}
		
		@GET
		@Path("/getAllCoachesInCertainSportFacility/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Coach> getAllCoachesInCertainSportFacility(@PathParam("id") long id) {
			Collection<TrainingDTO> trainingDTOs = getAllTrainingsInCertainSportFacility(id);
			Collection<Coach> coaches = new ArrayList<Coach>();
			for(TrainingDTO td : trainingDTOs) {
				if(!coaches.contains(td.getCoach())) {
					coaches.add(td.getCoach());
				}
			}
			
			return coaches;
		}
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public TrainingDTO get(@PathParam("id") long id) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			Training training = trainingService.get(id);
			TrainingDTO trainingDTO = new TrainingDTO();
			//return trainingService.transformFromTrainingToTrainingDTO(training, trainingDTO); //IMPLEMENT THIS METHOD IN SERVICE
			return null;
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public TrainingDTO create(Training training) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			Training training2 = trainingService.create(training);
			TrainingDTO trainingDTO = new TrainingDTO();
			//return trainingService.transformFromTrainingToTrainingDTO(training2, trainingDTO);
			return null;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public boolean update(Training training) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			return trainingService.update(training);
		}
		
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public boolean delete(@PathParam("id") long id) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			return trainingService.delete(id);
		}
}
