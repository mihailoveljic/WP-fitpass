package services.interfaces;


import java.util.ArrayList;
import java.util.Collection;

import beans.models.Guestbook;
import beans.models.SportsFacility;

public interface ISportsFacilityService extends ICRUDService<SportsFacility> {
	public ArrayList<SportsFacility> getByIds(ArrayList<Long> sportsFacilityIds);

	public void updateRating(long sportsFacilityId, Collection<Guestbook> sportsFacilityGuestbooks);
}
