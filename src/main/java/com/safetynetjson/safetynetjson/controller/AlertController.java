package com.safetynetjson.safetynetjson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;
import com.safetynetjson.safetynetjson.service.ChildByAddress;
import com.safetynetjson.safetynetjson.service.FirestationService;
import com.safetynetjson.safetynetjson.service.FloodStationService;
import com.safetynetjson.safetynetjson.service.PersonService;
import com.safetynetjson.safetynetjson.service.PersonsByStationNumber;
import com.safetynetjson.safetynetjson.vue.ChildAlertView;
import com.safetynetjson.safetynetjson.vue.FloodStationView;

@RestController
public class AlertController {

	private final PersonService personService;

	private final ChildByAddress childByAddress;

	private final ChildAlertView childAlertView;

	private final PersonsByStationNumber personsByStationNumber;

	private final FirestationService firestationService;

	private final FloodStationService floodStationService;

	private final FloodStationView floodStationView;

	public AlertController(PersonService personService, ChildByAddress childByAddress, ChildAlertView childAlertView,
			PersonsByStationNumber personsByStationNumber, FirestationService firestationService,
			FloodStationService floodStationService, FloodStationView floodStationView) {
		this.personService = personService;
		this.childByAddress = childByAddress;
		this.childAlertView = childAlertView;
		this.personsByStationNumber = personsByStationNumber;
		this.firestationService = firestationService;
		this.floodStationService = floodStationService;
		this.floodStationView = floodStationView;
	}

	@GetMapping("/childAlert")
	public Map<String, Object> getChildByAddress(@RequestParam("address") String address) {
		Map<String, Object> response = new HashMap<>();

		List<Person> personsWithoutAge = personService.getPersonsByAddress(address);

		List<PersonWithMedicalrecord> persons = childByAddress.listOfChildren(personsWithoutAge);

		response = childAlertView.formateChildAlert(persons);

		return response;

	}

	@GetMapping("/phoneAlert")
	public Map<String, List<String>> getPhoneByStationNumber(@RequestParam("station") Long station) {

		Map<String, List<String>> response = new HashMap<>();

		List<Person> persons = personsByStationNumber.listOfPersonsByStation(station);

		List<String> phoneNumbers = new ArrayList<>();

		for (Person person : persons) {
			String phoneNumber = person.getPhone();

			if (!phoneNumbers.contains(phoneNumber)) {
				phoneNumbers.add(phoneNumber);
			}
		}

		response.put("phone", phoneNumbers);
		return response;

	}

	@GetMapping("/flood/stations")
	public Map<String, Object> getPersonsByStations(@RequestParam("stationNumber") List<Long> stationNumbers) {
		Map<String, Object> response = new HashMap<>();

		List<String> coveredAddresses = floodStationService.getCoveredAddresses(stationNumbers);

		response = floodStationView.formatFloodStation(coveredAddresses,
				floodStationService.getPersonsData(coveredAddresses));

		return response;
	}

}
