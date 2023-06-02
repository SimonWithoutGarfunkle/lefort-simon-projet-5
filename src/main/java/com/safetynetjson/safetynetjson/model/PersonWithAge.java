package com.safetynetjson.safetynetjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonWithAge extends Person {

	@JsonProperty("isChild")
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public PersonWithAge(Person person, int age) {
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setAddress(person.getAddress());
		this.setCity(person.getCity());
		this.setZip(person.getZip());
		this.setPhone(person.getPhone());
		this.setEmail(person.getEmail());
		this.setAge(age);

	}

}
