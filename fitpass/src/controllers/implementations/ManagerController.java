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
import services.implementations.ContextInitService;
import services.interfaces.IManagerService;

@Path("/managers")
public class ManagerController implements ICRUDController<Manager> {


	@Context
	ServletContext ctx;
	
	public ManagerController() {}

	@PostConstruct
	public void init() {
		ContextInitService.initManagerService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<Manager> getAll(){
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Manager get(@PathParam("id") long id) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Manager create(Manager manager) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.create(manager);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Manager manager) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.update(manager);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.delete(id);
	}
}
