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

import beans.models.Buyer;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.IBuyerService;

@Path("/buyers")
public class BuyerController implements ICRUDController<Buyer> {


	@Context
	ServletContext ctx;
	
	public BuyerController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initBuyerService(ctx);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Buyer> getAll(){
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		return buyerService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Buyer get(@PathParam("id") long id) {
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		return buyerService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Buyer create(Buyer buyer) {
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		return buyerService.create(buyer);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Buyer buyer) {
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		return buyerService.update(buyer);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		return buyerService.delete(id);
	}
}
