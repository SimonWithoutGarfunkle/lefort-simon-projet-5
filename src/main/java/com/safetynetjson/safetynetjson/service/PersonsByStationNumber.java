package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

@Service
public class PersonsByStationNumber {

	private final JsonDataService jsonDataService;
	private final FirestationService firestationService;

	List<String> addressCovered = new ArrayList<>();

	public PersonsByStationNumber(JsonDataService jsonDataService, FirestationService firestationService) {
		this.jsonDataService = jsonDataService;
		this.firestationService = firestationService;
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
