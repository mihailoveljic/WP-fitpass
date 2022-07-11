package services.implementations;

import java.util.Collection;

import beans.models.MembershipType;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class MembershipTypeService implements ICRUDService<MembershipType> {

	private IDAO<MembershipType> membershipTypeDAO;
	
	
	
	public MembershipTypeService(IDAO<MembershipType> membershipTypeDAO) {
		super();
		this.membershipTypeDAO = membershipTypeDAO;
	}

	@Override
	public Collection<MembershipType> getAll() {
		return membershipTypeDAO.getAll();
	}

	@Override
	public MembershipType get(long id) {
		return membershipTypeDAO.get(String.valueOf(id));
	}

	@Override
	public MembershipType create(MembershipType membershipType) {
		return membershipTypeDAO.create(membershipType);
	}

	@Override
	public boolean update(MembershipType membershipType) {
		return membershipTypeDAO.update(membershipType);
	}

	@Override
	public boolean delete(long id) {
		return membershipTypeDAO.delete(String.valueOf(id));
	}
	
}
