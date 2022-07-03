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

import beans.dtos.BuyerDTO;
import beans.dtos.DateDTO;
import beans.dtos.FullTrainingDTO;
import beans.dtos.SportsFacilityDTO;
import beans.dtos.TrainingHistoryDTO;
import beans.models.Buyer;
import beans.models.BuyerType;
import beans.models.SportsFacility;
import beans.models.SportsFacilityType;
import beans.models.Training;
import beans.models.TrainingHistory;
import beans.models.TrainingType;
import services.implementations.ContextInitService;
import services.interfaces.IBuyerService;
import services.interfaces.ICRUDService;
import services.interfaces.ICoachService;
import services.interfaces.IFacilityContentService;
import services.interfaces.ISportsFacilityService;
import services.interfaces.ITrainingHistoryService;
import services.interfaces.ITrainingService;

@Path("/TrainingHistoryController")
public class TrainingHistoryController {


	@Context
	ServletContext ctx;
	
	public TrainingHistoryController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initTrainingHistoryService(ctx);
		ContextInitService.initCoachService(ctx);
		ContextInitService.initTrainingService(ctx);
		ContextInitService.initTrainingTypeService(ctx);
		ContextInitService.initBuyerService(ctx);
		ContextInitService.initBuyerTypeService(ctx);
		ContextInitService.initSportsFacilityService(ctx);
		ContextInitService.initSportsFacilityTypeService(ctx);
		ContextInitService.initFacilityContentService(ctx);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingHistoryDTO> getAll(){
		ITrainingHistoryService trainingHistoryService = (ITrainingHistoryService) ctx.getAttribute("TrainingHistoryService");
		ITrainingService trainingService = (ITrainingService) ctx.getAttribute("TrainingService");
		ICRUDService<TrainingType> trainingTypeService = (ICRUDService<TrainingType>) ctx.getAttribute("TrainingTypeService");
		ICoachService coachService = (ICoachService) ctx.getAttribute("CoachService");
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>) ctx.getAttribute("SportsFacilityTypeService");
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>) ctx.getAttribute("BuyerTypeService");
		
		Collection<TrainingHistory> trainingHistory = trainingHistoryService.getAll();
		Collection<TrainingHistoryDTO> trainingHistoryDTOs = new ArrayList<TrainingHistoryDTO>();
		trainingHistory.forEach(th -> {
			TrainingHistoryDTO dto = new TrainingHistoryDTO();
			FullTrainingDTO fullTrainingDTO = new FullTrainingDTO();
			BuyerDTO buyerDTO = new BuyerDTO();
			SportsFacilityDTO sportsFacilityDTO = new SportsFacilityDTO();
			
			Buyer b = buyerService.get(th.getBuyerId());
			Training t = trainingService.get(th.getTrainingId());
			
			fullTrainingDTO.setId(t.getId());
			fullTrainingDTO.setName(t.getName());
			fullTrainingDTO.setTrainingType(trainingTypeService.get(t.getTrainingTypeId()).getName());
			fullTrainingDTO.setId(t.getId());
			
			SportsFacility sportFacility = sportsFacilityService.get(t.getSportsFacilityId());
			sportsFacilityDTO.setId(sportFacility.getId());
			sportsFacilityDTO.setName(sportFacility.getName());
			sportsFacilityDTO.setSportsFacilityType(
					sportsFacilityTypeService.get(sportFacility.getSportsFacilityTypeId()).getName());
			sportsFacilityDTO.setFacilityContents(
					facilityContentService.getByIds(sportFacility.getFacilityContentIds()));
			sportsFacilityDTO.setOpenStatus(sportFacility.isOpenStatus());
			sportsFacilityDTO.setLocation(sportFacility.getLocation());
			sportsFacilityDTO.setImage(ctx.getContextPath() + "\\data\\img\\sports-facilities\\" + sportFacility.getImage());
			sportsFacilityDTO.setAverageRating(sportFacility.getAverageRating());
			sportsFacilityDTO.setWorkingHours(sportFacility.getWorkingHours());
			
			fullTrainingDTO.setSportsFacility(sportsFacilityDTO);
			fullTrainingDTO.setDuration(t.getDuration());
			fullTrainingDTO.setCoach(coachService.get(t.getCoachId()));
			fullTrainingDTO.setDescription(t.getDescription());
			fullTrainingDTO.setImage(t.getImage());
			fullTrainingDTO.setAdditionalPrice(t.getAdditionalPrice());
			
			buyerDTO.setId(b.getId());
			buyerDTO.setUsername(b.getUsername());
			buyerDTO.setName(b.getName());
			buyerDTO.setSurname(b.getSurname());
			buyerDTO.setGender(b.getGender());
			try {
				buyerDTO.setDateOfBirth(new DateDTO(
						b.getDateOfBirth().getYear() - 1900,
						b.getDateOfBirth().getMonth() + 1,
						b.getDateOfBirth().getDate()));
			} catch (Exception e) {return;}
			buyerDTO.setRole(b.getRole());
			buyerDTO.setMembership(null);
			buyerDTO.setVisitedSportsFacilities(sportsFacilityService.getByIds(b.getVisitedSportsFacilitiesIds()));
			buyerDTO.setNumberOfCollectedPoints(b.getNumberOfCollectedPoints());
			buyerDTO.setBuyerType(buyerTypeService.get(b.getBuyerTypeId()));
			
			dto.setDate(th.getDateTime());
			dto.setTraining(fullTrainingDTO);
			dto.setBuyer(buyerDTO);
			dto.setCoach(coachService.get(th.getCoachId()));
			trainingHistoryDTOs.add(dto);
		});
		return trainingHistoryDTOs;
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
