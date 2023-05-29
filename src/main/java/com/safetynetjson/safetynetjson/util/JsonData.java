package com.safetynetjson.safetynetjson.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Person;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonData {
	
    @JsonProperty("persons")
    private Person[] persons;
    
    @JsonProperty("firestations")
    private Firestation[] firestations;
    
	@JsonProperty("medicalrecords")
    private Medicalrecord[] medicalrecords;
    
    public Firestation[] getFirestations() {
		return firestations;
	}

	public void setFirestations(Firestation[] firestations) {
		this.firestations = firestations;
	}

	public Medicalrecord[] getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(Medicalrecord[] medicalrecords) {
		this.medicalrecords = medicalrecords;
	}


	public Person[] getPersons() {
		return persons;
	}

	public void setPersons(Person[] persons) {
		this.persons = persons;
	}

}
