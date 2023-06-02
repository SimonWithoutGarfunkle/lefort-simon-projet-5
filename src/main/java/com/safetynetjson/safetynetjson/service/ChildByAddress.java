package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithAge;

@Service
public class ChildByAddress {

	private final PersonWithAgeService personWithAgeService;
	public ChildByAddress(JsonDataService jsonDataService, PersonService personService,
			PersonWithAgeService personWithAgeService) {
		this.personWithAgeService = personWithAgeService;
	}

	public List<PersonWithAge> listOfChildren (List<Person> persons) {
		List<PersonWithAge> result = new ArrayList<PersonWithAge>();
		result = personWithAgeService.addAgeToPersons(persons);
		int countChildren = 0;
		for (PersonWithAge someone : result) {
			if (someone.getAge() <= 18) {
				countChildren +=1;
			}
			
		}
		if (countChildren==0) {
			result.clear();
			
		}
								
		return result;
	}

}
