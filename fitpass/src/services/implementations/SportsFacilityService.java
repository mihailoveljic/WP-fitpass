package services.implementations;

import java.util.ArrayList;
import java.util.Collection;

import beans.models.Guestbook;
import beans.models.SportsFacility;
import daos.interfaces.IDAO;
import services.interfaces.ISportsFacilityService;

public class SportsFacilityService implements ISportsFacilityService {

private IDAO<SportsFacility> sportsFacilityDAO;
	
	
	
	public SportsFacilityService(IDAO<SportsFacility> sportsFacilityDAO) {
		super();
		this.sportsFacilityDAO = sportsFacilityDAO;
	}

	@Override
	public Collection<SportsFacility> getAll() {
		return sportsFacilityDAO.getAll();
	}

	@Override
	public SportsFacility get(long id) {
		return sportsFacilityDAO.get(String.valueOf(id));
	}

	@Override
	public SportsFacility create(SportsFacility sportsFacility) {
		return sportsFacilityDAO.create(sportsFacility);
	}

	@Override
	public boolean update(SportsFacility sportsFacility) {
		return sportsFacilityDAO.update(sportsFacility);
	}

	@Override
	public boolean delete(long id) {
		return sportsFacilityDAO.delete(String.valueOf(id));
	}
	@Override
	public ArrayList<SportsFacility> getByIds(ArrayList<Long> sportsFacilityIds) {
		ArrayList<SportsFacility> sportsFacilities = new ArrayList<SportsFacility>();
		for(long sfi : sportsFacilityIds) {
			sportsFacilities.add(this.get(sfi));
		}
		return sportsFacilities;
	}

	@Override
	public void updateRating(long sportsFacilityId, Collection<Guestbook> sportsFacilityGuestbooks) {
		SportsFacility sportsFacility = sportsFacilityDAO.get(String.valueOf(sportsFacilityId));
		
		double rating = 0.0;		
		for(Guestbook g : sportsFacilityGuestbooks) {
			rating += g.getRating();
		}
		rating /= sportsFacilityGuestbooks.size();
		sportsFacility.setAverageRating(rating);
		update(sportsFacility);
	}


}
