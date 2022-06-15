package beans.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import beans.enums.Gender;
import beans.enums.Role;

public class BuyerRegistrationDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5479223749777028107L;
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private DateDTO dateOfBirth;
	private long membershipId;
	private long buyerTypeId;
	
	public BuyerRegistrationDTO() {}

	

}
