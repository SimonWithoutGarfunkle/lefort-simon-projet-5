package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

/**
 * Service dédié à l'alerte Flood Stations qui retourne une liste de tous les foyers desservis par les casernes spécifiées
 * @author Simon
 *
 */
@Service
public class FloodStationService {

	private final FirestationService firestationService;
	private final JsonDataService jsonDataService;
	private final MedicalrecordService medicalRecordService;
	
    private static Logger logger = LoggerFactory.getLogger(FloodStationService.class);

	public FloodStationService(FirestationService firestationService, JsonDataService jsonDataService,
			MedicalrecordService medicalRecordService) {
		this.firestationService = firestationService;
		this.jsonDataService = jsonDataService;
		this.medicalRecordService = medicalRecordService;
	}

	/**
	 * Retourne la liste des adresses couvertes par les casernes spécifiées et supprime les doublons
	 * 
	 * @param stationNumbers liste des casernes spécifiées
	 * @return Liste d'adresses couvertes sans doublon
	 */
	public List<String> getCoveredAddresses(List<Long> stationNumbers) {
		logger.info("Recuperation des adresses couvertes");
		List<String> coveredAddressesWithDuplicates = new ArrayList<>();
		for (Long stationNumber : stationNumbers) {
			coveredAddressesWithDuplicates.addAll(firestationService.addressCoveredByStation(stationNumber));
		}
		return new ArrayList<>(new LinkedHashSet<>(coveredAddressesWithDuplicates));
	}
	
	/**
	 * Recupere la liste des personnes habitants les adresses spécifiées
	 * Affiche leur nom, telephone, age, allergies et traitements
	 * 
	 * @param coveredAddresses
	 * @return liste des personnes habitants les adresses spécifiées
	 */
	public List<Map<String, Object>> getPersonsData(List<String> coveredAddresses) {
		logger.info("Recuperation des personnes habitants les adresses couvertes");
		List<Map<String, Object>> personsData = new ArrayList<>();
	    JsonData jsonData = jsonDataService.getJsonData();
	    List<Person> persons = jsonData.getPersons();
	    for (String address : coveredAddresses) {
	        List<Map<String, Object>> personsDataForAddress = new ArrayList<>();
	        for (Person someone : persons) {
	            if (address.equals(someone.getAddress())) {
	                Map<String, Object> personData = new HashMap<>();
	                personData.put("firstName", someone.getFirstName());
	                personData.put("lastName", someone.getLastName());
	                personData.put("phone", someone.getPhone());
	                personData.put("age", medicalRecordService.getAge(someone));
	                personData.put("allergies", medicalRecordService.getAllergies(someone));
	                personData.put("medications", medicalRecordService.getMedications(someone));
	                personsDataForAddress.add(personData);
	            }
	        }
	        Map<String, Object> addressData = new HashMap<>();
	        addressData.put(address, personsDataForAddress);
	        personsData.add(addressData);
	    }
	    return personsData;
	}

}
