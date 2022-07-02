package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import beans.models.MembershipType;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class MembershipTypeDAO implements IDAO<MembershipType>{

	private Map<String, MembershipType> membershipTypes = new HashMap<String, MembershipType>();
	private IRepository<MembershipType> membershipTypeRepository;
	
	public MembershipTypeDAO(IRepository<MembershipType> membershipTypeRepository) {
		super();
		this.membershipTypeRepository = membershipTypeRepository;
		membershipTypes = membershipTypeRepository.load();
	}

	@Override
	public Collection<MembershipType> getAll() {
		return membershipTypes.values();
	}

	@Override
	public MembershipType get(String id) {
		return membershipTypes.containsKey(id) ? membershipTypes.get(id) : null;
	}

	@Override
	public MembershipType create(MembershipType membershipType) {
		long maxId = 0;
		for (String id : membershipTypes.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		membershipType.setId(maxId);
		membershipTypes.put(String.valueOf(membershipType.getId()), membershipType);
		membershipTypeRepository.save(membershipTypes);
		return membershipType;
	}

	@Override
	public boolean update(MembershipType membershipType) {
		if(membershipTypes.put(String.valueOf(membershipType.getId()), membershipType) != null) {
			membershipTypeRepository.save(membershipTypes);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(membershipTypes.remove(id) != null) {
			membershipTypeRepository.save(membershipTypes);
			return true;
		}
		return false;
	}
}
