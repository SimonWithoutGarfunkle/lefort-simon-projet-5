package com.safetynetjson.safetynetjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonWithAge extends Person {

	@JsonProperty("isChild")
	private Boolean isChild;

	public Boolean getIsChild() {
		return isChild;
	}

	public void setIsChild(Boolean isChild) {
		this.isChild = isChild;
	}

	public PersonWithAge(Person person) {
		this(person, null);

	}
	
	public PersonWithAge(Person person, int age) {
		this(person, age<=18);

	}

	public PersonWithAge(Person person, Boolean bool) {
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setAddress(person.getAddress());
		this.setCity(person.getCity());
		this.setZip(person.getZip());
		this.setPhone(person.getPhone());
		this.setEmail(person.getEmail());
		this.setIsChild(bool);

	}

}
