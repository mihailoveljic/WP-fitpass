package beans.dtos;

import java.util.ArrayList;
import beans.enums.Gender;
import beans.enums.Role;
import beans.models.BuyerType;
import beans.models.Membership;
import beans.models.SportsFacility;

public class BuyerDTO{

	private long id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private DateDTO dateOfBirth; 
	private Role role;
	private Membership membership;
	private ArrayList<SportsFacility> visitedSportsFacilities;
	private double numberOfCollectedPoints;
	private BuyerType buyerType;
	
	public BuyerDTO() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public DateDTO getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateDTO dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public ArrayList<SportsFacility> getVisitedSportsFacilities() {
		return visitedSportsFacilities;
	}

	public void setVisitedSportsFacilities(ArrayList<SportsFacility> visitedSportsFacilities) {
		this.visitedSportsFacilities = visitedSportsFacilities;
	}

	public double getNumberOfCollectedPoints() {
		return numberOfCollectedPoints;
	}

	public void setNumberOfCollectedPoints(double numberOfCollectedPoints) {
		this.numberOfCollectedPoints = numberOfCollectedPoints;
	}

	public BuyerType getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(BuyerType buyerType) {
		this.buyerType = buyerType;
	}

	
	
}
