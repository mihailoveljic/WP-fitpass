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
import daos.implementations.BuyerDAO;
import daos.interfaces.IDAO;
import repositories.implementations.BuyerRepository;
import repositories.interfaces.IRepository;
import services.implementations.BuyerService;
import services.interfaces.ICRUDService;

@Path("/buyers")
public class BuyerController implements ICRUDController<Buyer> {


	@Context
	ServletContext ctx;
	
	public BuyerController() {}


	@PostConstruct
	public void init() {
		if (ctx.getAttribute("BuyerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Buyer> buyerRepository = new BuyerRepository(contextPath);
	    	
	    	IDAO<Buyer> buyerDAO = new BuyerDAO(buyerRepository);
	    	
			ctx.setAttribute("BuyerService", new BuyerService(buyerDAO));
		}
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Buyer> getAll(){
		ICRUDService<Buyer> buyerService = (ICRUDService<Buyer>) ctx.getAttribute("BuyerService");
		return buyerService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Buyer get(@PathParam("id") long id) {
		ICRUDService<Buyer> buyerService = (ICRUDService<Buyer>) ctx.getAttribute("BuyerService");
		return buyerService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Buyer create(Buyer buyer) {
		ICRUDService<Buyer> buyerService = (ICRUDService<Buyer>) ctx.getAttribute("BuyerService");
		return buyerService.create(buyer);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Buyer buyer) {
		ICRUDService<Buyer> buyerService = (ICRUDService<Buyer>) ctx.getAttribute("BuyerService");
		return buyerService.update(buyer);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		ICRUDService<Buyer> buyerService = (ICRUDService<Buyer>) ctx.getAttribute("BuyerService");
		return buyerService.delete(id);
	}
}
