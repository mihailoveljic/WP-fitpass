package services.implementations;

import java.time.LocalDate;
import java.util.ArrayList;

import beans.dtos.UserRegistrationDTO;
import beans.enums.Role;
import beans.models.Buyer;
import beans.models.Coach;
import beans.models.Manager;
import beans.models.TrainingHistory;
import services.interfaces.ICRUDService;
import services.interfaces.IRegisterService;

public class RegisterService implements IRegisterService {
	
	public RegisterService() {}
	private boolean validateUserFields(UserRegistrationDTO userRegistrationDTO) {
		if(userRegistrationDTO.getUsername()==null || userRegistrationDTO.getUsername().compareTo("") == 0) return false;
		if(userRegistrationDTO.getPassword()==null || userRegistrationDTO.getPassword().compareTo("") == 0) return false;
		if(userRegistrationDTO.getName()==null || userRegistrationDTO.getName().compareTo("") == 0) return false;
		if(userRegistrationDTO.getSurname()==null || userRegistrationDTO.getSurname().compareTo("") == 0) return false;
		if(userRegistrationDTO.getGender()==null) return false;
		if(userRegistrationDTO.getDateOfBirth() == null) return false;
		return true;
	}

	@Override
	public Buyer registerBuyer(UserRegistrationDTO userRegistrationDTO, ICRUDService<Buyer> service) {
		if(!validateUserFields(userRegistrationDTO)) return null;
		Buyer buyer = new Buyer();
		buyer.setBuyerTypeId(1);
		try {
			buyer.setDateOfBirth(LocalDate.of(
					userRegistrationDTO.getDateOfBirth().getYear(),
					userRegistrationDTO.getDateOfBirth().getMonth(),
					userRegistrationDTO.getDateOfBirth().getDay()));
		} catch (Exception e) {return null;}
		
		buyer.setGender(userRegistrationDTO.getGender());
		buyer.setMembershipId(-1);
		buyer.setName(userRegistrationDTO.getName());
		buyer.setNumberOfCollectedPoints(0);
		buyer.setPassword(userRegistrationDTO.getPassword());
		buyer.setRole(Role.KUPAC);
		buyer.setSurname(userRegistrationDTO.getSurname());
		buyer.setUsername(userRegistrationDTO.getUsername());
		buyer.setVisitedSportsFacilitiesIds(new ArrayList<Long>());
		return service.create(buyer);
	}

	@Override
	public Coach registerCoach(UserRegistrationDTO userRegistrationDTO, ICRUDService<Coach> service) {
		if(!validateUserFields(userRegistrationDTO)) return null;
		Coach coach = new Coach();
		try {
			coach.setDateOfBirth(LocalDate.of(
					userRegistrationDTO.getDateOfBirth().getYear(),
					userRegistrationDTO.getDateOfBirth().getMonth(),
					userRegistrationDTO.getDateOfBirth().getDay()));
		} catch (Exception e) {return null;}
		coach.setGender(userRegistrationDTO.getGender());
		coach.setName(userRegistrationDTO.getName());
		coach.setPassword(userRegistrationDTO.getPassword());
		coach.setRole(Role.TRENER);
		coach.setSurname(userRegistrationDTO.getSurname());
		coach.setUsername(userRegistrationDTO.getUsername());
		coach.setTrainingHistory(new ArrayList<TrainingHistory>());
		return service.create(coach);
	}

	@Override
	public Manager registerManager(UserRegistrationDTO userRegistrationDTO, ICRUDService<Manager> service) {
		if(!validateUserFields(userRegistrationDTO)) return null;
		Manager manager = new Manager();
		try {
			manager.setDateOfBirth(LocalDate.of(
					userRegistrationDTO.getDateOfBirth().getYear(),
					userRegistrationDTO.getDateOfBirth().getMonth(),
					userRegistrationDTO.getDateOfBirth().getDay()));
		} catch (Exception e) {return null;}
		manager.setGender(userRegistrationDTO.getGender());
		manager.setName(userRegistrationDTO.getName());
		manager.setPassword(userRegistrationDTO.getPassword());
		manager.setRole(Role.TRENER);
		manager.setSurname(userRegistrationDTO.getSurname());
		manager.setUsername(userRegistrationDTO.getUsername());
		manager.setSportsFacilityId(-1);
		return service.create(manager);
	}

}
