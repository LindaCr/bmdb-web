package com.bmdb.business;

import java.time.LocalDate;

import javax.persistence.*;


@Entity
public class Actor {

	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate birthdate;
	
	public Actor() {
		super();
	}


	public Actor(int id, String firstName, String lastName, String gender, LocalDate birthdate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate=birthdate;
	}

	public Actor(String firstName, String lastName, String gender, LocalDate birthdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate=birthdate;
	}

	
	public int getid() {
		return id;
	}


	public void setid(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}


	@Override
	public String toString() {
		return "Actor [ID=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", birthdate=" + birthdate + "]";
	}

	public String getFullName() {
		return firstName+" "+lastName;
	}
	
	
}
