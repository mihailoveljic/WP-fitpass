package services.implementations;

import java.lang.reflect.Member;
import java.util.Collection;

import beans.models.Membership;
import daos.interfaces.IDAO;
import services.interfaces.IMembershipService;

public class MembershipService implements IMembershipService {

private IDAO<Membership> membershipDAO;
	
	
	
	public MembershipService(IDAO<Membership> membershipDAO) {
		super();
		this.membershipDAO = membershipDAO;
	}

	@Override
	public Collection<Membership> getAll() {
		return membershipDAO.getAll();
	}

	@Override
	public Membership get(long id) {
		return membershipDAO.get(String.valueOf(id));
	}

	@Override
	public Membership create(Membership membership) {
		return membershipDAO.create(membership);
	}

	@Override
	public boolean update(Membership membership) {
		return membershipDAO.update(membership);
	}

	@Override
	public boolean delete(long id) {
		return membershipDAO.delete(String.valueOf(id));
	}

	@Override
	public Membership getByBuyer(long id) {
		Collection<Membership> memberships = membershipDAO.getAll();
		for(Membership m : memberships) {
			if(m.getBuyerId()==id) return m;
		}
		return null;
	}

}
