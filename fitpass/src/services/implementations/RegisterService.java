package services.implementations;

import java.util.ArrayList;

import beans.enums.Role;
import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import beans.models.SportsFacility;
import beans.models.TrainingHistory;
import beans.models.User;
import services.interfaces.ICRUDService;
import services.interfaces.IRegisterService;

public class RegisterService implements IRegisterService {
	
	public RegisterService() {}
	private boolean validateUserFields(User user) {
		if(user.getUsername().compareTo("") == 0) return false;
		if(user.getPassword().compareTo("") == 0) return false;
		if(user.getName().compareTo("") == 0) return false;
		if(user.getSurname().compareTo("") == 0) return false;
		if(user.getGender()==null) return false;
		if(user.getDateOfBirth() == null) return false;
		return true;
	}

	@Override
	public Buyer registerBuyer(Buyer buyer, ICRUDService<Buyer> service) {
		if(!validateUserFields(buyer)) return null;
		if(!validateBuyerFields(buyer)) return null;
		return service.create(buyer);
	}

	private boolean validateBuyerFields(Buyer buyer) {
		if(buyer.getRole() != Role.KUPAC) return false;
		if(buyer.getMembership() == null) return false;
		if(buyer.getVisitedSportsFacilities() == null) {
			ArrayList<SportsFacility> visitedSportsFacilities = new ArrayList<>();
			buyer.setVisitedSportsFacilities(visitedSportsFacilities);
		}
		if(buyer.getBuyerType()==null) return false;
		return true;
	}
	@Override
	public Coach registerCoach(Coach coach, ICRUDService<Coach> service) {
		if(!validateUserFields(coach)) return null;
		if(!validateCoachFields(coach)) return null;
		return service.create(coach);
	}

	private boolean validateCoachFields(Coach coach) {
		if(coach.getRole() != Role.TRENER) return false;
		/*if(coach.getTrainingHistory() == null) {
			coach.setTrainingHistory(new ArrayList<TrainingHistory>());
		}*/
		return true;
	}
	@Override
	public Manager registerManager(Manager manager, ICRUDService<Manager> service) {
		if(!validateUserFields(manager)) return null;
		if(!validateManagerFields(manager)) return null;
		return service.create(manager);
	}

	private boolean validateManagerFields(Manager manager) {
		if(manager.getRole() != Role.MENADZER) return false;
		if(manager.getSportsFacility()==null) return false;
		return true;
	}
	@Override
	public Administrator registerAdministrator(Administrator administrator, ICRUDService<Administrator> service) {
		if(!validateUserFields(administrator)) return null;
		if(!validateAdministratorFields(administrator)) return null;
		return service.create(administrator);
	}
	private boolean validateAdministratorFields(Administrator administrator) {
		if(administrator.getRole() != Role.ADMINISTRATOR) return false;
		return true;
	}

}
