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

import beans.dtos.SportsFacilityDTO;
import beans.models.SportsFacility;
import beans.models.SportsFacilityType;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ICRUDService;
import services.interfaces.IFacilityContentService;
import services.interfaces.ISportsFacilityService;

@Path("/SportsFacilityController")
public class SportsFacilityController implements ICRUDController<SportsFacility, SportsFacilityDTO> {

	@Context
	ServletContext ctx;
	
	public SportsFacilityController() {}


	@PostConstruct
	public void init() {
		ContextInitService.initSportsFacilityService(ctx);
		ContextInitService.initSportsFacilityTypeService(ctx);
		ContextInitService.initFacilityContentService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<SportsFacilityDTO> getAll(){
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>)ctx.getAttribute("SportsFacilityTypeService");
		IFacilityContentService facilityContentService = (IFacilityContentService)ctx.getAttribute("FacilityContentService");
		Collection<SportsFacility> sportsFacilities = sportsFacilityService.getAll();
		Collection<SportsFacilityDTO> sportsFacilityDTOs = new ArrayList<SportsFacilityDTO>();
		
		for(SportsFacility sportFacility : sportsFacilities) {
			SportsFacilityDTO sportsFacilityDTO = new SportsFacilityDTO();
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
			sportsFacilityDTOs.add(sportsFacilityDTO);
		}
		return sportsFacilityDTOs;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public SportsFacilityDTO get(@PathParam("id") long id) {
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService)ctx.getAttribute("SportsFacilityService");
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>)ctx.getAttribute("SportsFacilityTypeService");
		IFacilityContentService facilityContentService = (IFacilityContentService)ctx.getAttribute("FacilityContentService");
		
		SportsFacility sportFacility = sportsFacilityService.get(id);
		
		SportsFacilityDTO sportsFacilityDTO = new SportsFacilityDTO();
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

		return sportsFacilityDTO;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public SportsFacilityDTO create(SportsFacility sportsFacility) {
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");
		ICRUDService<SportsFacilityType> sportsFacilityTypeService = (ICRUDService<SportsFacilityType>)ctx.getAttribute("SportsFacilityTypeService");
		IFacilityContentService facilityContentService = (IFacilityContentService)ctx.getAttribute("FacilityContentService");	
		SportsFacility sportFacility = sportsFacilityService.create(sportsFacility);
		SportsFacilityDTO sportsFacilityDTO = new SportsFacilityDTO();
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

		return sportsFacilityDTO;
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
