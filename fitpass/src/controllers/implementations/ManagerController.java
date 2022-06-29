package controllers.implementations;

import java.util.Collection;
import java.util.Date;

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

import beans.dtos.ManagerFacilityUpdateDTO;
import beans.dtos.UserUpdateDTO;
import beans.models.Manager;
import services.implementations.ContextInitService;
import services.interfaces.IManagerService;

@Path("/managers")
public class ManagerController{


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
	public Collection<Manager> getAll(){
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.getAll();
	}
	
	@GET
	@Path("/free")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Manager> getFree(){
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.getFree();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Manager get(@PathParam("id") long id) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.get(id);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Manager create(Manager manager) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.create(manager);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(UserUpdateDTO userUpdateDTO) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		Manager manager = managerService.get(userUpdateDTO.getId());
		if(!userUpdateDTO.getOldPassword().equals(manager.getPassword())) return false;
		
		if(userUpdateDTO.isChangePassword()) {
			manager.setPassword(userUpdateDTO.getNewPassword());
		}
		manager.setName(userUpdateDTO.getName());
		manager.setSurname(userUpdateDTO.getSurname());
		manager.setGender(userUpdateDTO.getGender());
		try {
			manager.setDateOfBirth(new Date(
					userUpdateDTO.getDateOfBirth().getYear(),
					userUpdateDTO.getDateOfBirth().getMonth(),
					userUpdateDTO.getDateOfBirth().getDay()));
		} catch (Exception e) {return false;}
		
		return managerService.update(manager);
	}
	@PUT
	@Path("/updateFacility")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateFacility(ManagerFacilityUpdateDTO newManager) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		Manager manager = managerService.get(newManager.getManagerId());
		
		manager.setSportsFacilityId(newManager.getFacilityId());

		return managerService.update(manager);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") long id) {
		IManagerService managerService = (IManagerService) ctx.getAttribute("ManagerService");
		return managerService.delete(id);
	}
}
