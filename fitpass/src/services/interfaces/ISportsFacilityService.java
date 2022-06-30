package services.interfaces;


import java.util.ArrayList;

import beans.models.SportsFacility;

public interface ISportsFacilityService extends ICRUDService<SportsFacility> {
	public ArrayList<SportsFacility> getByIds(ArrayList<Long> sportsFacilityIds);
}
