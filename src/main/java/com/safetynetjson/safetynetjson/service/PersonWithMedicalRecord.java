package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;

@Service
public class PersonWithMedicalRecord {

	private final JsonDataService jsonDataService;
	private final MedicalrecordService medicalrecordService;

	public PersonWithMedicalRecord(JsonDataService jsonDataService, MedicalrecordService medicalrecordService) {
		this.jsonDataService = jsonDataService;
		this.medicalrecordService = medicalrecordService;
	}

	public boolean isAChild(Person person) {
		if (medicalrecordService.getAge(person) > 18) {
			return false;
		}
		return true;

	}

	public List<PersonWithMedicalrecord> addAgeToPersons(List<Person> persons) {
		List<PersonWithMedicalrecord> result = new ArrayList<PersonWithMedicalrecord>();
		for (Person someone : persons) {
			result.add(new PersonWithMedicalrecord(someone, medicalrecordService.getAge(someone)));
		}

		return result;
	}
	
	public int countChild(List<PersonWithMedicalrecord> personsWithAge) {
		int child = 0;
		for (PersonWithMedicalrecord someone : personsWithAge) {
			if (someone.getAge() <= 18 ) {
				child += 1;
				
			} 
		}
		return child;
		
	}
	
	public int countPeople(List<PersonWithMedicalrecord> personsWithAge) {
		return personsWithAge.size();
		
	}
	

}
