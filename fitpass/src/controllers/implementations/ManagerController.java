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

import beans.models.Manager;
import controllers.interfaces.ICRUDController;
import daos.implementations.ManagerDAO;
import daos.interfaces.IDAO;
import repositories.implementations.ManagerRepository;
import repositories.interfaces.IRepository;
import services.implementations.ManagerService;
import services.interfaces.ICRUDService;

@Path("/managers")
public class ManagerController implements ICRUDController<Manager> {


	@Context
	ServletContext ctx;
	
	public ManagerController() {}


	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("ManagerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Manager> managerRepository = new ManagerRepository(contextPath);
	    	
	    	IDAO<Manager> managerDAO = new ManagerDAO(managerRepository);
	    	
			ctx.setAttribute("ManagerService", new ManagerService(managerDAO));
		}
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Manager> getAll(){
		ICRUDService<Manager> managerService = (ICRUDService<Manager>) ctx.getAttribute("ManagerService");
		return managerService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Manager get(@PathParam("id") long id) {
		ICRUDService<Manager> managerService = (ICRUDService<Manager>) ctx.getAttribute("ManagerService");
		return managerService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Manager create(Manager manager) {
		ICRUDService<Manager> managerService = (ICRUDService<Manager>) ctx.getAttribute("ManagerService");
		return managerService.create(manager);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Manager manager) {
		ICRUDService<Manager> managerService = (ICRUDService<Manager>) ctx.getAttribute("ManagerService");
		return managerService.update(manager);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		ICRUDService<Manager> managerService = (ICRUDService<Manager>) ctx.getAttribute("ManagerService");
		return managerService.delete(id);
	}
}
