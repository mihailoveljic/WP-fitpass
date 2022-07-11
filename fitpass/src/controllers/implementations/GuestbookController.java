package controllers.implementations;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import beans.dtos.GuestbookDTO;
import beans.dtos.SportsFacilityDTO;
import beans.dtos.UserToken;
import beans.enums.ApprovalStatus;
import beans.enums.Role;
import beans.models.Buyer;
import beans.models.BuyerType;
import beans.models.Guestbook;
import beans.models.SportsFacility;
import beans.models.SportsFacilityType;
import services.implementations.ContextInitService;
import services.interfaces.IBuyerService;
import services.interfaces.ICRUDService;
import services.interfaces.IFacilityContentService;
import services.interfaces.IGuestbookService;
import services.interfaces.ISportsFacilityService;

@Path("/GuestbookController")
public class GuestbookController {


	@Context
	ServletContext ctx;
	
	public GuestbookController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initGuesbookService(ctx);
		ContextInitService.initBuyerService(ctx);
		ContextInitService.initBuyerTypeService(ctx);
		ContextInitService.initSportsFacilityService(ctx);
		ContextInitService.initSportsFacilityTypeService(ctx);
		ContextInitService.initFacilityContentService(ctx);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<GuestbookDTO> getAll(@Context HttpServletRequest request){
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>) ctx.getAttribute("BuyerTypeService");
		
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>) ctx.getAttribute("SportsFacilityTypeService");
		
		UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
		

		Collection<Guestbook> guestbooks;
		
		if(userIsAdministratorOrManager(userToken)) guestbooks = guestbookService.getAll();
		else guestbooks = guestbookService.getAllApproved();
		
		Collection<GuestbookDTO> guestbookDTOs = new ArrayList<GuestbookDTO>();
		
		guestbooks.forEach(guestbook -> {
			GuestbookDTO dto = new GuestbookDTO();
			BuyerDTO buyerDTO = new BuyerDTO();
			SportsFacilityDTO sportsFacilityDTO = new SportsFacilityDTO();
			

			Buyer b = buyerService.get(guestbook.getBuyerId());
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
			
			
			SportsFacility sportFacility = sportsFacilityService.get(guestbook.getSportsFacilityId());
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
			
			dto.setId(guestbook.getId());
			dto.setBuyer(buyerDTO);
			dto.setSportsFacility(sportsFacilityDTO);
			dto.setComment(guestbook.getComment());
			dto.setRating(guestbook.getRating());
			dto.setApprovalStatus(guestbook.getApprovalStatus());
			
			guestbookDTOs.add(dto);
		});
		return guestbookDTOs;
	}

	@GET
	@Path("/GetAllForSportFacility/{sportsFacilityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<GuestbookDTO> getAllForSportFacility(@Context HttpServletRequest request, @PathParam("sportsFacilityId") long sportsFacilityId){
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>) ctx.getAttribute("BuyerTypeService");
		
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>) ctx.getAttribute("SportsFacilityTypeService");
		
		
		
		UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");

		
		
		Collection<Guestbook> guestbooks;
		
		if(userIsAdministratorOrManager(userToken)) {
			guestbooks = guestbookService.getAllForSportsFacilityId(sportsFacilityId);
		}else {
			guestbooks = guestbookService.getAllApprovedForSportsFacilityId(sportsFacilityId);
		}

		Collection<GuestbookDTO> guestbookDTOs = new ArrayList<GuestbookDTO>();
		
		guestbooks.forEach(guestbook -> {
			GuestbookDTO dto = new GuestbookDTO();
			BuyerDTO buyerDTO = new BuyerDTO();
			SportsFacilityDTO sportsFacilityDTO = new SportsFacilityDTO();
			

			Buyer b = buyerService.get(guestbook.getBuyerId());
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
			
			
			SportsFacility sportFacility = sportsFacilityService.get(guestbook.getSportsFacilityId());
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
			
			dto.setId(guestbook.getId());
			dto.setBuyer(buyerDTO);
			dto.setSportsFacility(sportsFacilityDTO);
			dto.setComment(guestbook.getComment());
			dto.setRating(guestbook.getRating());
			dto.setApprovalStatus(guestbook.getApprovalStatus());
			
			guestbookDTOs.add(dto);
		});
		return guestbookDTOs;
	}
	
	private boolean userIsAdministratorOrManager(UserToken userToken) {
		if (userToken != null) {
			if(userToken.getRole() == Role.ADMINISTRATOR || userToken.getRole() == Role.MENADZER) {
				return true;
			}
		}
		return false;
	}
	private boolean userIsAdministrator(UserToken userToken) {
		if (userToken != null) {
			if(userToken.getRole() == Role.ADMINISTRATOR) {
				return true;
			}
		}
		return false;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Guestbook get(@PathParam("id") long id) {
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		
		return guestbookService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Guestbook create(Guestbook guestbook) {
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		guestbook.setApprovalStatus(ApprovalStatus.WAITING_FOR_APPROVAL);
		return guestbookService.create(guestbook);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(Guestbook guestbook) {
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");

		boolean retVal = guestbookService.update(guestbook);
		Collection<Guestbook> sportsFacilityGuestbooks = guestbookService.getAllApprovedForSportsFacilityId(guestbook.getSportsFacilityId());
		sportsFacilityService.updateRating(guestbook.getSportsFacilityId(), sportsFacilityGuestbooks);
		return retVal;
	}
	
	@PUT
	@Path("/approve/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean approve(@Context HttpServletRequest request, @PathParam("id") long id) {
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");

		UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
		
		if(!userIsAdministrator(userToken)) return false;
		
		Guestbook guestbook = guestbookService.get(id);
		
		guestbook.setApprovalStatus(ApprovalStatus.APPROVED);
		
		boolean retVal = guestbookService.update(guestbook);
		Collection<Guestbook> sportsFacilityGuestbooks = guestbookService.getAllApprovedForSportsFacilityId(guestbook.getSportsFacilityId());
		sportsFacilityService.updateRating(guestbook.getSportsFacilityId(), sportsFacilityGuestbooks);
		return retVal;
	}
	
	@PUT
	@Path("/disapprove/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean disapprove(@Context HttpServletRequest request, @PathParam("id") long id) {
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");
		
		UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
		
		if(!userIsAdministrator(userToken)) return false;
		
		Guestbook guestbook = guestbookService.get(id);
		
		guestbook.setApprovalStatus(ApprovalStatus.DISAPPROVED);
		
		return guestbookService.update(guestbook);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") long id) {
		IGuestbookService guestbookService = (IGuestbookService) ctx.getAttribute("GuestbookService");	
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");

		Guestbook guestbook = guestbookService.get(id);
		if(guestbook == null) return false;
		
		boolean retVal = guestbookService.delete(guestbook.getId());
		Collection<Guestbook> sportsFacilityGuestbooks = guestbookService.getAllApprovedForSportsFacilityId(guestbook.getSportsFacilityId());
		sportsFacilityService.updateRating(guestbook.getSportsFacilityId(), sportsFacilityGuestbooks);
		return retVal;
	}
}
