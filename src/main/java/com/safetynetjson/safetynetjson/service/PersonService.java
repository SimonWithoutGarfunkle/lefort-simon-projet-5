package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

@Service
public class PersonService {

	private final JsonDataService jsonDataService;

	public PersonService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	private List<Person> getPersons() {
		JsonData jsonData = jsonDataService.getJsonData();
		return jsonData.getPersons();
	}

	public boolean isPresent(Person person) {
		List<Person> persons = getPersons();
		for (Person someone : persons) {
			if (someone.getFirstName().equals(person.getFirstName())
					&& (someone.getLastName().equals(person.getLastName()))) {
				return true;
			}
		}

		return false;

	}

	public Person returnPersonFromBase(Person person) {
		List<Person> persons = getPersons();
		if (isPresent(person)) {
			for (Person someone : persons) {
				if (someone.getFirstName().equals(person.getFirstName())
						&& (someone.getLastName().equals(person.getLastName()))) {
					return someone;
				}
			}

		}
		return null;

	}

	public void addPerson(Person person) {
		List<Person> persons = getPersons();
		persons.add(person);

	}

	public void removePerson(Person person) {
		Person personToRemove = returnPersonFromBase(person);
		List<Person> persons = getPersons();
		persons.remove(personToRemove);

	}

	public void updatePerson(Person person) {
		Person personToUpdate = returnPersonFromBase(person);
		personToUpdate.setAddress(person.getAddress());
		personToUpdate.setCity(person.getCity());
		personToUpdate.setZip(person.getZip());
		personToUpdate.setPhone(person.getPhone());
		personToUpdate.setEmail(person.getEmail());

	}

	public void patchPerson(Person person) {
		Person personToUpdate = returnPersonFromBase(person);
		if (!(person.getAddress() == null)) {
			personToUpdate.setAddress(person.getAddress());
		}
		if (!(person.getCity() == null)) {
			personToUpdate.setCity(person.getCity());
		}
		if (!(person.getZip() == null)) {
			personToUpdate.setZip(person.getZip());

		}
		if (!(person.getPhone() == null)) {
			personToUpdate.setPhone(person.getPhone());
		}
		if (!(person.getEmail() == null)) {
			personToUpdate.setEmail(person.getEmail());

		}

	}

	public List<Person> getPersonsByAddress(String address) {
		List<Person> persons = getPersons();
		List<Person> result = new ArrayList<Person>();
		for (Person someone : persons) {
			if (someone.getAddress().equals(address)) {
				result.add(someone);

			}
		}
		return result;
	}

}
