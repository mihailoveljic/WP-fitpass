package services.implementations;

import java.util.Collection;

import beans.models.SportsFacilityType;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class SportsFacilityTypeService implements ICRUDService<SportsFacilityType> {
private IDAO<SportsFacilityType> sportsFacilityTypeDAO;
	
	
	
	public SportsFacilityTypeService(IDAO<SportsFacilityType> sportsFacilityTypeDAO) {
		super();
		this.sportsFacilityTypeDAO = sportsFacilityTypeDAO;
	}

	@Override
	public Collection<SportsFacilityType> getAll() {
		return sportsFacilityTypeDAO.getAll();
	}

	@Override
	public SportsFacilityType get(long id) {
		return sportsFacilityTypeDAO.get(String.valueOf(id));
	}

	@Override
	public SportsFacilityType create(SportsFacilityType sportsFacilityType) {
		return sportsFacilityTypeDAO.create(sportsFacilityType);
	}

	@Override
	public boolean update(SportsFacilityType sportsFacilityType) {
		return sportsFacilityTypeDAO.update(sportsFacilityType);
	}

	@Override
	public boolean delete(long id) {
		return sportsFacilityTypeDAO.delete(String.valueOf(id));
	}

}
