package services.implementations;

import java.util.ArrayList;

import java.util.Collection;

import beans.models.FacilityContent;
import daos.interfaces.IDAO;
import services.interfaces.IFacilityContentService;

public class FacilityContentService implements IFacilityContentService {

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
		Collection<FacilityContent> facilityContents = facilityContentDAO.getAll();
		for(FacilityContent fc : facilityContents) {
			if(fc.getName().equals(facilityContent.getName())) {
				return fc;
			}
		}
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

	@Override
	public ArrayList<String> getByIds(ArrayList<Long> facilityContentIds) {
		ArrayList<String> facilityContenNames = new ArrayList<String>();
		for(long fci : facilityContentIds) {
			facilityContenNames.add(this.get(fci).getName());
		}
		return facilityContenNames;
	}

	@Override
	public FacilityContent getByName(String name) {
		Collection<FacilityContent> facilityContents = facilityContentDAO.getAll();
		for(FacilityContent fc : facilityContents) {
			if(fc.getName().compareTo(name)==0) {
				return fc;
			}
		}
		return null;
	}


}
