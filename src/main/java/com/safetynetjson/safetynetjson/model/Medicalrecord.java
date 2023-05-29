package com.safetynetjson.safetynetjson.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicalrecord {
	
    @JsonProperty("firstName")
	private String firstName;

    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("birthdate")
    private Date birthdate;
    
    @JsonProperty("medications")
    private String[] medications;
    
    @JsonProperty("allergies")
    private String[] allergies;

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	@JsonFormat(pattern = "MM/dd/yyyy")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String[] getMedications() {
		return medications;
	}

	public void setMedications(String[] medications) {
		this.medications = medications;
	}

	public String[] getAllergies() {
		return allergies;
	}

	public void setAllergies(String[] allergies) {
		this.allergies = allergies;
	}

}
