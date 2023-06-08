package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

@Service
public class CommunityEmail {

	private final JsonDataService jsonDataService;

	public CommunityEmail(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	public List<String> getCommunityEmail(String city) {
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
