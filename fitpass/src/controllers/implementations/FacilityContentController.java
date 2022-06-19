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
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.IFacilityContentService;

@Path("/FacilityContentController")
public class FacilityContentController implements ICRUDController<FacilityContent, FacilityContent> {

	@Context
	ServletContext ctx;
	
	public FacilityContentController() {}


	@PostConstruct
	public void init() {
		ContextInitService.initFacilityContentService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<FacilityContent> getAll(){
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public FacilityContent get(@PathParam("id") long id) {
		IFacilityContentService facilityContentService = (IFacilityContentService)ctx.getAttribute("FacilityContentService");
		return facilityContentService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public FacilityContent create(FacilityContent facilityContent) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.create(facilityContent);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(FacilityContent facilityContent) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.update(facilityContent);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		IFacilityContentService facilityContentService = (IFacilityContentService) ctx.getAttribute("FacilityContentService");
		return facilityContentService.delete(id);
	}
	

}
