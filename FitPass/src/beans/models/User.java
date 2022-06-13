package beans.models;

import java.io.Serializable;
import java.sql.Date;

import beans.enums.Gender;
import beans.enums.Role;

public abstract class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5786200437024664103L;
	private long id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private Date dateOfBirth; 
	private Role role;
	
	public User() {}

	public User(long id, String korisnickoIme, String password, String name, String surname, Gender gender, Date dateOfBirth,
			Role role) {
		super();
		this.id = id;
		this.username = korisnickoIme;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String korisnickoIme) {
		this.username = korisnickoIme;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	
}
