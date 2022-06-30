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

import beans.dtos.TrainingDTO;
import beans.models.Training;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ITrainingService;

@Path("/TrainingController")
public class TrainingController implements ICRUDController<Training, TrainingDTO> {

		@Context
		ServletContext ctx;
		
		public TrainingController() {}

		@PostConstruct
		public void init() {
			//ContextInitService.initBuyerService(ctx);
			//ContextInitService.initBuyerTypeService(ctx);
			//ContextInitService.initSportsFacilityService(ctx);
			ContextInitService.initTrainingService(ctx);
		}
		
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public Collection<TrainingDTO> getAll(){
			/*BuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
			ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
			ICRUDService<BuyerType> buyerTypesService = (ICRUDService<BuyerType>) ctx.getAttribute("BuyerTypeService");
			Collection<Buyer> buyers = buyerService.getAll();
			Collection<BuyerDTO> buyersDTOs = new ArrayList<BuyerDTO>();
			
			for(Buyer b : buyers) {
				BuyerDTO buyerDTO = new BuyerDTO();
				buyerDTO.setId(b.getId());
				buyerDTO.setUsername(b.getUsername());
				buyerDTO.setPassword(b.getPassword());
				buyerDTO.setName(b.getName());
				buyerDTO.setSurname(b.getSurname());
				buyerDTO.setGender(b.getGender());
				try {
					buyerDTO.setDateOfBirth(new DateDTO(
							b.getDateOfBirth().getYear() - 1900,
							b.getDateOfBirth().getMonth() + 1,
							b.getDateOfBirth().getDate()));
				} catch (Exception e) {return null;}
				buyerDTO.setRole(b.getRole());
				buyerDTO.setMembership(null);
				buyerDTO.setVisitedSportsFacilities(sportsFacilityService.getByIds(b.getVisitedSportsFacilitiesIds()));
				buyerDTO.setNumberOfCollectedPoints(b.getNumberOfCollectedPoints());
				buyerDTO.setBuyerType(buyerTypesService.get(b.getBuyerTypeId()));
				buyersDTOs.add(buyerDTO);
			}
			return buyersDTOs;*/
			return null;
		}
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public TrainingDTO get(@PathParam("id") long id) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			Training training = trainingService.get(id);
			TrainingDTO trainingDTO = new TrainingDTO();
			return trainingService.transformFromTrainingToTrainingDTO(training, trainingDTO); //IMPLEMENT THIS METHOD IN SERVICE
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public TrainingDTO create(Training training) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			Training training2 = trainingService.create(training);
			TrainingDTO trainingDTO = new TrainingDTO();
			return trainingService.transformFromTrainingToTrainingDTO(training2, trainingDTO);
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public boolean update(Training training) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			return trainingService.update(training);
		}
		
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public boolean delete(@PathParam("id") long id) {
			ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
			return trainingService.delete(id);
		}
}
