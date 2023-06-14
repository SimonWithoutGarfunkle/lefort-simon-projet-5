package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

/**
 * Retourne les personnes couvertes par le numero de caserne spécifié
 * @author Simon
 *
 */
@Service
public class PersonsByStationNumber {

	private final JsonDataService jsonDataService;
	private final FirestationService firestationService;
	private static Logger logger = LoggerFactory.getLogger(PersonsByStationNumber.class);


	List<String> addressCovered = new ArrayList<>();

	public PersonsByStationNumber(JsonDataService jsonDataService, FirestationService firestationService) {
		this.jsonDataService = jsonDataService;
		this.firestationService = firestationService;
	}

	/**
	 * Retourne les personnes couvertes par le numero de caserne spécifié
	 * @param station
	 * @return Liste de personnes couvertes par le numero de caserne spécifié
	 */
	public List<Person> listOfPersonsByStation(Long station) {

		logger.info("Recuperation des personnes couvertes par la caserne n°"+station);
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
