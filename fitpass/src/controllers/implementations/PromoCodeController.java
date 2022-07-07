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

import beans.models.PromoCode;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.IPromoCodeService;

@Path("PromoCodeController")
public class PromoCodeController {

	@Context
	ServletContext ctx;
	
	public PromoCodeController() {}


	@PostConstruct
	public void init() {
		ContextInitService.initPromoCodeService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PromoCode> getAll(){
		IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
		return promoCodeService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode get(@PathParam("id") long id) {
		IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
		return promoCodeService.get(id);
	}
	
	@GET
	@Path("/checkIfPromoCodeExists/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode getByMark(@PathParam("id") String id) {
		IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
		return promoCodeService.checkIfPromoCodeExists(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PromoCode create(PromoCode promoCode) {
		IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
		return promoCodeService.create(promoCode);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(PromoCode promoCode) {
		IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
		return promoCodeService.update(promoCode);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") long id) {
		IPromoCodeService promoCodeService = (IPromoCodeService) ctx.getAttribute("PromoCodeService");
		return promoCodeService.delete(id);
	}
	
}
