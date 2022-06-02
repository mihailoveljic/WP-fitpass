package beans.models;

import java.sql.Date;

import beans.enums.Gender;
import beans.enums.Role;

public abstract class User {
	private double id;
	private String korisnickoIme;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private Date dateOfBirth; 
	private Role role;
	
	public User() {}

	public User(double id, String korisnickoIme, String password, String name, String surname, Gender gender, Date dateOfBirth,
			Role role) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
	}
	
	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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
