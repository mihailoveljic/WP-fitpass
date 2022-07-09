package daos.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.Membership;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class MembershipDAO implements IDAO<Membership> {

	private Map<String, Membership> memberships = new HashMap<String, Membership>();
	private IRepository<Membership> membershipRepository;
	
	public MembershipDAO(IRepository<Membership> membershipRepository) {
		super();
		this.membershipRepository = membershipRepository;
		memberships = membershipRepository.load();
	}

	@Override
	public Collection<Membership> getAll() {
		Collection<Membership> retVal = new ArrayList<Membership>(memberships.values());
		retVal.removeIf(x -> (x.getIsDeleted()));
		return retVal;
	}

	@Override
	public Membership get(String id) {
		if(memberships.containsKey(id)) {
			if(memberships.get(id).getIsDeleted() == false){
				return memberships.get(id);
			}
		}
		return null;	
	}

	@Override
	public Membership create(Membership membership) {
		long maxId = 0;
		for (String id : memberships.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		membership.setId(maxId);
		memberships.put(String.valueOf(membership.getId()), membership);
		membershipRepository.save(memberships);
		return membership;
	}

	@Override
	public boolean update(Membership membership) {
		if(memberships.put(String.valueOf(membership.getId()), membership) != null) {
			membershipRepository.save(memberships);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(memberships.containsKey(id)) {
			memberships.get(id).setIsDeleted(true);
			membershipRepository.save(memberships);
			return true;
		}
		return false;
	}
}
