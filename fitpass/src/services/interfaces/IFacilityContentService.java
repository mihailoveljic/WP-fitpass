package services.interfaces;

import java.util.ArrayList;

import beans.models.FacilityContent;

public interface IFacilityContentService extends ICRUDService<FacilityContent> {
	public ArrayList<String> getByIds(ArrayList<Long> facilityContentIds);
	public FacilityContent getByName(String name);
}
