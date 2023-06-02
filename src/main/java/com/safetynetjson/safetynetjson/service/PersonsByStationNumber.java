package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithAge;

@Service
public class PersonsByStationNumber {

	private final JsonDataService jsonDataService;
	private final FirestationService firestationService;
	private final PersonWithAgeService personWithAgeService;

	List<String> addressCovered = new ArrayList<>();

	public PersonsByStationNumber(JsonDataService jsonDataService, FirestationService firestationService, PersonWithAgeService personWithAgeService) {
		this.jsonDataService = jsonDataService;
		this.firestationService = firestationService;
		this.personWithAgeService = personWithAgeService;
	}

	public List<Person> listOfPersonsByStation(Long station) {

		JsonData jsonData = jsonDataService.getJsonData();

		addressCovered = firestationService.addressCoveredByStation(station);

		List<Person> result = new ArrayList<Person>();

		List<Person> persons = jsonData.getPersons();

		for (Person someone : persons) {
			if (addressCovered.contains(someone.getAddress())) {
				result.add(someone);

			}
		}

		return result;

	}
	
	

}
