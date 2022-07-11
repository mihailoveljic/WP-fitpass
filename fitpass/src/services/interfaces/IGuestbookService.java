package services.interfaces;

import java.util.Collection;


import beans.models.Guestbook;

public interface IGuestbookService extends ICRUDService<Guestbook> {

	Collection<Guestbook> getAll();

	Collection<Guestbook> getAllApproved();

	Collection<Guestbook> getAllForSportsFacilityId(long sportsFacilityId);

	Collection<Guestbook> getAllApprovedForSportsFacilityId(long sportsFacilityId);

	void deleteForBuyer(long id);

	
}
