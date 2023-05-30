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

		jsonData.setPersons(persons);

	}
	
	public void removePerson(Person person) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		List<Person> iterator = new ArrayList<>(persons);
		for (Person someone : iterator) {
			if (someone.getFirstName().equals((person).getFirstName())) {
				if (someone.getLastName().equals(person.getLastName())) {
					persons.remove(someone);
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

}
