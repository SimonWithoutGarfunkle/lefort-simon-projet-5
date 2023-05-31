package com.safetynetjson.safetynetjson.service;

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

	public boolean isPresent(Person person) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		for (Person someone : persons) {
			if (someone.getFirstName().equals(person.getFirstName())) {
				if (someone.getLastName().equals(person.getLastName())) {
					return true;
				}
			}

		}
		return false;

	}

	public void addPerson(Person person) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();

		persons.add(person);

	}

	public void removePerson(Person person) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		for (Person someone : persons) {
			if (someone.getFirstName().equals((person).getFirstName())) {
				if (someone.getLastName().equals(person.getLastName())) {
					persons.remove(someone);
					break;
				}

			}
		}

	}

	public void updatePerson(Person person) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		for (Person someone : persons) {
			if (someone.getFirstName().equals((person).getFirstName())) {
				if (someone.getLastName().equals(person.getLastName())) {
					someone.setAddress(person.getAddress());
					someone.setCity(person.getCity());
					someone.setZip(person.getZip());
					someone.setPhone(person.getPhone());
					someone.setEmail(person.getEmail());
				}

			}
		}

	}

	public void patchPerson(Person person) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		for (Person someone : persons) {
			if (someone.getFirstName().equals((person).getFirstName())) {
				if (someone.getLastName().equals(person.getLastName())) {
					if (!(person.getAddress() == null)) {
						someone.setAddress(person.getAddress());
					}
					if (!(person.getCity() == null)) {
						someone.setCity(person.getCity());
					}
					if (!(person.getZip() == null)) {
						someone.setZip(person.getZip());

					}
					if (!(person.getPhone() == null)) {
						someone.setPhone(person.getPhone());
					}
					if (!(person.getEmail() == null)) {
						someone.setEmail(person.getEmail());
					}

				}

			}
		}

	}

}
