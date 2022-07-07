package controllers.implementations;

import java.util.ArrayList;
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
import beans.models.PromoCode;
import services.implementations.ContextInitService;
import services.interfaces.IBuyerService;
import services.interfaces.IMembershipService;
import services.interfaces.IPromoCodeService;

@Path("/MembershipController")
public class MembershipController {

	@Context
	ServletContext ctx;
	
	public MembershipController() {}
	
	@PostConstruct
	public void init() {
		ContextInitService.initMembershipService(ctx);
		ContextInitService.initBuyerService(ctx);
		ContextInitService.initPromoCodeService(ctx);
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Membership> getAll(){
		//IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		//Collection<Membership> memberships = membershipService.getAll();
		return null;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Membership get(@PathParam("id") long id) {
		//IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		//Membership membership = membershipService.get(id);
		return null;
	}
	
	@GET
	@Path("/byBuyer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Membership getByBuyer(@PathParam("id") long id) {
		IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		Membership membership = membershipService.getByBuyer(id);
		return membership;
	}
	
	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Membership create(Membership membership, @PathParam("id") String id) {
		IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		IBuyerService buyerService = (IBuyerService)ctx.getAttribute("BuyerService");
		IPromoCodeService promoCodeService = (IPromoCodeService)ctx.getAttribute("PromoCodeService");
		
		if(id!="") {
			PromoCode promoCode = promoCodeService.checkIfPromoCodeExists(id);
			if(promoCodeService.isPromoCodeValid(promoCode)) {
				promoCode.setHowManyTimeCanBeUsed(promoCode.getHowManyTimeCanBeUsed()-1);
				promoCodeService.update(promoCode);
			}
		}
		long membershipId = buyerService.invalidateMembershipIfExists(membership.getBuyerId());
		if(membershipId != -1) {
			membershipService.delete(membershipId);
		}
		ArrayList<Long> membershipNumbers = membershipService.getAllMembershipNumbers();
		long number = 0;
		do {
			number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		}while(membershipNumbers.contains(number));
		membership.setMembershipNumber(number);
		membership = membershipService.create(membership);
		return membership;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean update(Membership membership) {
		IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		return membershipService.update(membership);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean delete(@PathParam("id") long id) {
		IMembershipService membershipService = (IMembershipService)ctx.getAttribute("MembershipService");
		return membershipService.delete(id);
	}
}
