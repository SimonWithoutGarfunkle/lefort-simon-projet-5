package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;

@Service
public class ChildByAddress {

	private final PersonWithMedicalRecord personWithAgeService;
	public ChildByAddress(JsonDataService jsonDataService, PersonService personService,
			PersonWithMedicalRecord personWithAgeService) {
		this.personWithAgeService = personWithAgeService;
	}

	public List<PersonWithMedicalrecord> listOfChildren (List<Person> persons) {
		List<PersonWithMedicalrecord> result = new ArrayList<PersonWithMedicalrecord>();
		result = personWithAgeService.addAgeToPersons(persons);
		int countChildren = 0;
		for (PersonWithMedicalrecord someone : result) {
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
