package beans.dtos;


import beans.enums.Gender;

public class UserUpdateDTO {
	
	private long id;
	private String username;
	private String oldPassword;
	private String newPassword;
	private boolean changePassword;
	private String name;
	private String surname;
	private Gender gender;
	private DateDTO dateOfBirth;
	
	public UserUpdateDTO() { }

	
	
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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
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

	
	

}
