package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;

@Service
public class PersonInfo {
	
	private final JsonDataService jsonDataService;
	private final MedicalrecordService medicalrecordService;
	
	public PersonInfo(PersonService personService, JsonDataService jsonDataService, MedicalrecordService medicalrecordService) {
		this.jsonDataService = jsonDataService;
		this.medicalrecordService = medicalrecordService;
	}
	
	/**
	 * Retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies) de la personne.
	 * Si plusieurs personnes portent le même nom et prénom, elles doivent toutes apparaître.
	 * 
	 * @return Json avec les infos de toutes les personnes qui correspondent au nom et prénom spécifiés
	 */
	public List<PersonWithMedicalrecord> getPersonInfo(String firstName, String lastName) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();		
		List<PersonWithMedicalrecord> personsInfo = new ArrayList<PersonWithMedicalrecord>();
		for (Person someone : persons) {
			if(someone.getFirstName().equals(firstName) &&
					someone.getLastName().equals(lastName)) {
				PersonWithMedicalrecord someoneWithMedicalRecord = 
						new PersonWithMedicalrecord(someone, medicalrecordService.getAge(someone), medicalrecordService.getMedications(someone), medicalrecordService.getAllergies(someone) );
				personsInfo.add(someoneWithMedicalRecord);
			}
		}
				
		return personsInfo;
	}

}
