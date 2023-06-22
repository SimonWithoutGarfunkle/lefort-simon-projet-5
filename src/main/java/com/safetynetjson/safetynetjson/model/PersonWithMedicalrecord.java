package com.safetynetjson.safetynetjson.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonWithMedicalrecord extends Person {

	@JsonProperty("age")
	private int age;

	@JsonProperty("medications")
	private List<String> medications;

	@JsonProperty("allergies")
	private List<String> allergies;

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PersonWithMedicalrecord(Person person, int age, List<String> medications, List<String> allergies) {
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setAddress(person.getAddress());
		this.setCity(person.getCity());
		this.setZip(person.getZip());
		this.setPhone(person.getPhone());
		this.setEmail(person.getEmail());
		this.setAge(age);
		this.medications = medications;
		this.allergies = allergies;

	}

	public PersonWithMedicalrecord(Person person, int age) {
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setAddress(person.getAddress());
		this.setCity(person.getCity());
		this.setZip(person.getZip());
		this.setPhone(person.getPhone());
		this.setEmail(person.getEmail());
		this.setAge(age);
		this.medications = new ArrayList<>();
		this.allergies = new ArrayList<>();

	}

}
