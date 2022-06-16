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

import beans.models.Administrator;
import controllers.interfaces.ICRUDController;
import daos.implementations.AdministratorDAO;
import daos.interfaces.IDAO;
import repositories.implementations.AdministratorRepository;
import repositories.interfaces.IRepository;
import services.implementations.AdministratorService;
import services.interfaces.IAdministratorService;

@Path("/administrators")
public class AdministratorController implements ICRUDController<Administrator> {


	@Context
	ServletContext ctx;
	
	public AdministratorController() {}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("AdministratorService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Administrator> buyerRepository = new AdministratorRepository(contextPath);
	    	
	    	IDAO<Administrator> administratorDAO = new AdministratorDAO(buyerRepository);
	    	
			ctx.setAttribute("AdministratorService", new AdministratorService(administratorDAO));
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Administrator> getAll(){
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		return administratorService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Administrator get(@PathParam("id") long id) {
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		return administratorService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Administrator create(Administrator administrator) {
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		return administratorService.create(administrator);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Administrator administrator) {
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		return administratorService.update(administrator);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		IAdministratorService administratorService = (IAdministratorService) ctx.getAttribute("AdministratorService");
		return administratorService.delete(id);
	}
}
