package services.implementations;

import java.util.Collection;

import beans.models.FacilityContent;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class FacilityContentService implements ICRUDService<FacilityContent> {

private IDAO<FacilityContent> facilityContentDAO;
	
	
	
	public FacilityContentService(IDAO<FacilityContent> facilityContentDAO) {
		super();
		this.facilityContentDAO = facilityContentDAO;
	}

	@Override
	public Collection<FacilityContent> getAll() {
		return facilityContentDAO.getAll();
	}

	@Override
	public FacilityContent get(long id) {
		return facilityContentDAO.get(String.valueOf(id));
	}

	@Override
	public FacilityContent create(FacilityContent facilityContent) {
		return facilityContentDAO.create(facilityContent);
	}

	@Override
	public boolean update(FacilityContent facilityContent) {
		return facilityContentDAO.update(facilityContent);
	}

	@Override
	public boolean delete(long id) {
		return facilityContentDAO.delete(String.valueOf(id));
	}


}
