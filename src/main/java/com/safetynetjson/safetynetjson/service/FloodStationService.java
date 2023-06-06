package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

@Service
public class FloodStationService {

	private final FirestationService firestationService;
	private final JsonDataService jsonDataService;
	private final MedicalrecordService medicalRecordService;

	public FloodStationService(FirestationService firestationService, JsonDataService jsonDataService,
			MedicalrecordService medicalRecordService) {
		this.firestationService = firestationService;
		this.jsonDataService = jsonDataService;
		this.medicalRecordService = medicalRecordService;
	}

	public List<String> getCoveredAddresses(List<Long> stationNumbers) {
		List<String> coveredAddressesWithDuplicates = new ArrayList<>();
		for (Long stationNumber : stationNumbers) {
			coveredAddressesWithDuplicates.addAll(firestationService.addressCoveredByStation(stationNumber));
		}
		return new ArrayList<>(new LinkedHashSet<>(coveredAddressesWithDuplicates));
	}
	
	public List<Map<String, Object>> getPersonsData(List<String> coveredAddresses) {
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
