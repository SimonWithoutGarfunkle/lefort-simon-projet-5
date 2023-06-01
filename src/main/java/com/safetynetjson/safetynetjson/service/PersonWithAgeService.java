package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithAge;

@Service
public class PersonWithAgeService {

	private final JsonDataService jsonDataService;
	private final MedicalrecordService medicalrecordService;

	public PersonWithAgeService(JsonDataService jsonDataService, MedicalrecordService medicalrecordService) {
		this.jsonDataService = jsonDataService;
		this.medicalrecordService = medicalrecordService;
	}

	public boolean isAChild(Person person) {
		if (medicalrecordService.getAge(person) > 18) {
			return false;
		}
		return true;

	}

	public List<PersonWithAge> addAgeToPersons(List<Person> persons) {
		List<PersonWithAge> result = new ArrayList<PersonWithAge>();
		for (Person someone : persons) {
			result.add(new PersonWithAge(someone, medicalrecordService.getAge(someone)));
		}

		return result;
	}

}
