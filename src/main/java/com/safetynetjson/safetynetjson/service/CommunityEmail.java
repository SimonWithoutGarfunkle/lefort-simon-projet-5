package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

/**
 * Service dédié à l'alerte CommunityEmail qui retourne les adresses mail de tous les habitants de la ville.
 * @author Simon
 *
 */
@Service
public class CommunityEmail {

	private final JsonDataService jsonDataService;
	
    private static Logger logger = LoggerFactory.getLogger(CommunityEmail.class);

	public CommunityEmail(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	/**
	 * Retourne la liste des emails des habitants de la ville spécifiée sans doublon
	 * @param city
	 * @return json des emails des habitants de la ville
	 */
	public List<String> getCommunityEmail(String city) {
		logger.info("Recuperation des emails de la ville "+city);
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		List<String> emailsOfCity = new ArrayList<String>();
		for (Person someone : persons) {
			if (someone.getCity().equals(city)) {
				emailsOfCity.add(someone.getEmail());
			}
		}

		return emailsOfCity;

	}

}
