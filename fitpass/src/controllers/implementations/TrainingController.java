package controllers.implementations;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

		public static int MAX_SIZE_IN_MB = 5;
		
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
				trainingDTO.setAdditionalPrice(t.getAdditionalPrice());
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
				trainingDTO.setAdditionalPrice(t.getAdditionalPrice());
				trainingDTOs.add(trainingDTO);
			}
			return trainingDTOs;
		}
		
		@GET
		@Path("/getAllTrainingsForCoach/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<TrainingDTO> getAllTrainingsForCoach(@PathParam("id") long id) {
			ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
			
			Collection<Training> trainings = trainingService.getAllTrainingsForCoach(id);
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
				trainingDTO.setAdditionalPrice(t.getAdditionalPrice());
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
			ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
			
			Training t = trainingService.get(id);
			
			TrainingDTO trainingDTO = new TrainingDTO();
			trainingDTO.setId(t.getId());
			trainingDTO.setName(t.getName());
			trainingDTO.setTrainingType(trainingTypeService.get(t.getTrainingTypeId()));
			trainingDTO.setSportsFacilityId(t.getSportsFacilityId());
			trainingDTO.setDuration(t.getDuration());
			trainingDTO.setCoach(coachService.get(t.getCoachId()));
			trainingDTO.setDescription(t.getDescription());
			trainingDTO.setImage(ctx.getContextPath() + "\\data\\img\\trainings\\" + t.getImage());
			trainingDTO.setAdditionalPrice(t.getAdditionalPrice());
			return trainingDTO;
		}
		
		@POST
		@Path("/uploadImage")
	    @Consumes({"image/jpeg", "image/png"})
	    public Response uploadImage(InputStream in, @HeaderParam("Content-Type") String fileType, @HeaderParam("Content-Length") long fileSize) throws IOException {
	        
	        // Make sure the file is not larger than the maximum allowed size.
	        if (fileSize > 1024 * 1024 * MAX_SIZE_IN_MB) {
	            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Image is larger than " + MAX_SIZE_IN_MB + "MB").build());
	        }
	        
	        // Generate a random file name based on the current time.
	        // This probably isn't 100% safe but works fine for this example.
	        String fileName = "" + System.currentTimeMillis();

	        if (fileType.equals("image/jpeg")) {
	            fileName += ".jpg";
	        } else {
	            fileName += ".png";
	        }
	        
	        File f = new File(ctx.getRealPath("data\\img\\trainings\\" + fileName));
	        System.out.println(f.getAbsolutePath());

	        // Copy the file to its location.
	        Files.copy(in, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        in.close();
	        
	        // Return a 201 Created response with the appropriate Location header.
	        return Response.status(Status.CREATED).location(URI.create("/" + fileName)).build();
	    }
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Training create(TrainingDTO trainingDTO) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>)ctx.getAttribute("TrainingTypeService");
			//Training training2 = trainingService.create(training);
			//TrainingDTO trainingDTO = new TrainingDTO();
			//return trainingService.transformFromTrainingToTrainingDTO(training2, trainingDTO);
			Training training = new Training();
			training.setId(trainingDTO.getId());
			training.setName(trainingDTO.getName());
			if(trainingDTO.getTrainingType().getId() == -1) {
				training.setTrainingTypeId((trainingTypeService.create(trainingDTO.getTrainingType())).getId());
			}
			else {
				training.setTrainingTypeId(trainingDTO.getTrainingType().getId());
			}
			training.setSportsFacilityId(trainingDTO.getSportsFacilityId());
			training.setDuration(trainingDTO.getDuration());
			training.setCoachId(trainingDTO.getCoach().getId());
			training.setDescription(trainingDTO.getDescription());
			training.setImage(trainingDTO.getImage());
			training.setAdditionalPrice(trainingDTO.getAdditionalPrice());
			training.setIsDeleted(false);
			return trainingService.create(training);
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public boolean update(TrainingDTO trainingDTO) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>)ctx.getAttribute("TrainingTypeService");
			Training training = new Training();
			training.setId(trainingDTO.getId());
			training.setName(trainingDTO.getName());
			if(trainingDTO.getTrainingType().getId() == -1) {
				training.setTrainingTypeId((trainingTypeService.create(trainingDTO.getTrainingType())).getId());
			}
			else {
				training.setTrainingTypeId(trainingDTO.getTrainingType().getId());
			}
			training.setSportsFacilityId(trainingDTO.getSportsFacilityId());
			training.setDuration(trainingDTO.getDuration());
			training.setCoachId(trainingDTO.getCoach().getId());
			training.setDescription(trainingDTO.getDescription());
			training.setImage(trainingDTO.getImage());
			training.setAdditionalPrice(trainingDTO.getAdditionalPrice());
			training.setIsDeleted(false);
			return trainingService.update(training);
		}
		
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public boolean delete(@PathParam("id") long id) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			Collection<Training> trainings = trainingService.getAllTrainingsForCoach(id);
			if(!trainings.isEmpty()) return false;
			return trainingService.delete(id);
		}
}
