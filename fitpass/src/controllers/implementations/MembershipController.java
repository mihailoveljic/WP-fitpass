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

import beans.models.Membership;
import beans.models.MembershipDTO;
import controllers.interfaces.IMembershipController;
import services.implementations.ContextInitService;
import services.interfaces.IMembershipService;

public class MembershipController implements IMembershipController {

	@Context
	ServletContext ctx;
	
	public MembershipController() {}
	
	@PostConstruct
	public void init() {
		ContextInitService.initMembershipService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Collection<MembershipDTO> getAll(){
		//IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		//Collection<Membership> memberships = membershipService.getAll();
		return null;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public MembershipDTO get(@PathParam("id") long id) {
		//IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		//Membership membership = membershipService.get(id);
		return null;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public MembershipDTO create(Membership membership) {
		//IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		//Membership membership1 = membershipService.create(membership);
		return null;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean update(Membership membership) {
		IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		return membershipService.update(membership);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public boolean delete(@PathParam("id") long id) {
		IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		return membershipService.delete(id);
	}
}
