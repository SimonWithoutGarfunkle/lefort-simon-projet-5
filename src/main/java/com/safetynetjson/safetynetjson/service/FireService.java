package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;

/**
 * Service dédié à l'alerte Fire
 * retourne la liste des habitants vivant à l’adresse donnée et le numéro de la caserne la desservant
 * 
 * @author Simon
 *
 */
@Service
public class FireService {
	private final PersonService personService;
	private final MedicalrecordService medicalRecordService;
	private final FirestationService firestationService;
    private static Logger logger = LoggerFactory.getLogger(FireService.class);


	public FireService(PersonService personService, MedicalrecordService medicalRecordService,
			FirestationService firestationService) {
		this.personService = personService;
		this.medicalRecordService = medicalRecordService;
		this.firestationService = firestationService;
	}

	/**
	 * Retourne la liste des habitants vivants à l’adresse donnée
	 * Retourne les noms, téléphone, caserne, age, allergies et traitements
	 * 
	 * @param address
	 * @return liste des habitants vivants
	 */
	public List<Map<String, Object>> getPersonsForFire(String address) {
		logger.info("Recuperation des habitants a l'adresse");
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
