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

import beans.models.BuyerType;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ICRUDService;

@Path("/BuyerTypeController")
public class BuyerTypeController implements ICRUDController<BuyerType, BuyerType> {

	@Context
	ServletContext ctx;
	
	public BuyerTypeController() {}
	
	@PostConstruct
	public void init() {
		ContextInitService.initBuyerTypeService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<BuyerType> getAll(){
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>) ctx.getAttribute("BuyerTypeService");
		return buyerTypeService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BuyerType get(@PathParam("id") long id) {
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>)ctx.getAttribute("BuyerTypeService");
		return buyerTypeService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BuyerType create(BuyerType buyerType) {
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>)ctx.getAttribute("BuyerTypeService");
		return buyerTypeService.create(buyerType);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(BuyerType buyerType) {
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>)ctx.getAttribute("BuyerTypeService");
		return buyerTypeService.update(buyerType);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		ICRUDService<BuyerType> buyerTypeService = (ICRUDService<BuyerType>)ctx.getAttribute("BuyerTypeService");
		return buyerTypeService.delete(id);
	}
	
}
