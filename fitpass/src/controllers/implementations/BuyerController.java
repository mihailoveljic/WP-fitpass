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

import beans.dtos.UserUpdateDTO;
import beans.models.Buyer;
import controllers.interfaces.IBuyerController;
import services.implementations.ContextInitService;
import services.interfaces.IBuyerService;

@Path("/buyers")
public class BuyerController implements IBuyerController {


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
	
	@SuppressWarnings("deprecation")
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(UserUpdateDTO userUpdateDTO) {
		IBuyerService buyerService = (IBuyerService) ctx.getAttribute("BuyerService");
		Buyer buyer = buyerService.get(userUpdateDTO.getId());
		if(!userUpdateDTO.getOldPassword().equals(buyer.getPassword())) return false;
		
		if(userUpdateDTO.isChangePassword()) {
			buyer.setPassword(userUpdateDTO.getNewPassword());
		}
		buyer.setName(userUpdateDTO.getName());
		buyer.setSurname(userUpdateDTO.getSurname());
		buyer.setGender(userUpdateDTO.getGender());
		try {
			buyer.setDateOfBirth(new Date(
					userUpdateDTO.getDateOfBirth().getYear(),
					userUpdateDTO.getDateOfBirth().getMonth(),
					userUpdateDTO.getDateOfBirth().getDay()));
		} catch (Exception e) {return false;}
		
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
