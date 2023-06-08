package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;

@Service
public class FireService {
	private final PersonService personService;
	private final MedicalrecordService medicalRecordService;
	private final FirestationService firestationService;

	public FireService(PersonService personService, MedicalrecordService medicalRecordService,
			FirestationService firestationService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
		this.firestationService = firestationService;
	}

	public List<Map<String, Object>> getPersonsForFire(String address) {
		List<Map<String, Object>> personsForFire = new ArrayList<>();
		Long stationNumber = firestationService.getStationFromAddress(address);

		List<Person> persons = personService.getPersonsByAddress(address);

		for (Person someone : persons) {
			Map<String, Object> personForFire = new HashMap<>();
			personForFire.put("firstName", someone.getFirstName());
			personForFire.put("lastName", someone.getLastName());
			personForFire.put("phone", someone.getPhone());
			personForFire.put("station", stationNumber);
			personForFire.put("age", medicalRecordService.getAge(someone));
			personForFire.put("allergies", medicalRecordService.getAllergies(someone));
			personForFire.put("medications", medicalRecordService.getMedications(someone));
			personsForFire.add(personForFire);
		}

		return personsForFire;

	}

}
