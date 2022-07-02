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

import beans.models.MembershipType;
import controllers.interfaces.ICRUDController;
import services.implementations.ContextInitService;
import services.interfaces.ICRUDService;

@Path ("MembershipTypeController")
public class MembershipTypeController implements ICRUDController<MembershipType, MembershipType>{


		@Context
		ServletContext ctx;
		
		public MembershipTypeController() {}

		@PostConstruct
		public void init() {
			ContextInitService.initMembershipTypeService(ctx);
		}
		
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public Collection<MembershipType> getAll(){
			ICRUDService<MembershipType> membershipTypeService = (ICRUDService<MembershipType>)ctx.getAttribute("MembershipTypeService");
			return membershipTypeService.getAll();
		}
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public MembershipType get(@PathParam("id") long id) {
			ICRUDService<MembershipType> membershipTypeService = (ICRUDService<MembershipType>)ctx.getAttribute("MembershipTypeService");
			return membershipTypeService.get(id);
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public MembershipType create(MembershipType membershipType) {
			ICRUDService<MembershipType> membershipTypeService = (ICRUDService<MembershipType>)ctx.getAttribute("MembershipTypeService");
			return membershipTypeService.create(membershipType);
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public boolean update(MembershipType membershipType) {
			return false;
		}
		
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Override
		public boolean delete(@PathParam("id") long id) {
			return false;
		}
}
