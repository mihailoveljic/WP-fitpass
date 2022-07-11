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

import beans.models.FacilityContent;
import beans.models.SportsFacility;
import services.implementations.ContextInitService;
import services.interfaces.IFacilityContentService;
import services.interfaces.ISportsFacilityService;

@Path("/FacilityContentController")
public class FacilityContentController{

	@Context
	ServletContext ctx;
	
	public FacilityContentController() {}


	@PostConstruct
	public void init() {
		ContextInitService.initFacilityContentService(ctx);
		ContextInitService.initSportsFacilityService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<FacilityContent> getAll(){
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FacilityContent get(@PathParam("id") long id) {
		IFacilityContentService facilityContentService = (IFacilityContentService)ctx.getAttribute("FacilityContentService");
		return facilityContentService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FacilityContent create(FacilityContent facilityContent) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.create(facilityContent);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(FacilityContent facilityContent) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.update(facilityContent);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") String name) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		
		return facilityContentService.delete(facilityContentService.getByName(name).getId());
	}
	
	@DELETE
	@Path("/{contentName}/{facilityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("contentName") String contentName, @PathParam("facilityId") long facilityId) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		ISportsFacilityService sportsFacilityService = (ISportsFacilityService) ctx.getAttribute("SportsFacilityService");

		Long contentId = facilityContentService.getByName(contentName).getId();
		SportsFacility sportsFacility = sportsFacilityService.get(facilityId);
		sportsFacility.getFacilityContentIds().removeIf(id->(id == contentId));
		return sportsFacilityService.update(sportsFacility);
	}

}
