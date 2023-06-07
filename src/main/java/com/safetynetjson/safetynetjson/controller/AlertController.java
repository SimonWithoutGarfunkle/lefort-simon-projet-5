package com.safetynetjson.safetynetjson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;
import com.safetynetjson.safetynetjson.service.ChildByAddress;
import com.safetynetjson.safetynetjson.service.FloodStationService;
import com.safetynetjson.safetynetjson.service.PersonService;
import com.safetynetjson.safetynetjson.service.PersonsByStationNumber;
import com.safetynetjson.safetynetjson.vue.ChildAlertView;
import com.safetynetjson.safetynetjson.vue.FloodStationView;

/**
 * expose les GET pour les requetes complexes.
 * 
 * @author Simon
 *
 */
@RestController
public class AlertController {

	private final PersonService personService;

	private final ChildByAddress childByAddress;

	private final ChildAlertView childAlertView;

	private final PersonsByStationNumber personsByStationNumber;

	private final FloodStationService floodStationService;

	private final FloodStationView floodStationView;

	public AlertController(PersonService personService, ChildByAddress childByAddress, ChildAlertView childAlertView,
			PersonsByStationNumber personsByStationNumber, 
			FloodStationService floodStationService, FloodStationView floodStationView) {
		this.personService = personService;
		this.childByAddress = childByAddress;
		this.childAlertView = childAlertView;
		this.personsByStationNumber = personsByStationNumber;
		this.floodStationService = floodStationService;
		this.floodStationView = floodStationView;
	}

	/**
	 * Cette URL renvoie la liste des enfants habitants l'adresse indiquée avec leur nom complet et leur age.
	 * S'il y a au moins un enfant, renvoi également la liste des adultes à cette adresse.
	 * S'il n'y a pas d'enfant à cette adresse, renvoie un json vide.
	 * 
	 * @param address String avec n° et nom de rue
	 * @return json avec les listes d'enfants et adultes habitants à l'adresse spécifiée
	 */
	@GetMapping("/childAlert")
	public Map<String, Object> getChildByAddress(@RequestParam("address") String address) {
		Map<String, Object> response = new HashMap<>();

		List<Person> personsWithoutAge = personService.getPersonsByAddress(address);

		List<PersonWithMedicalrecord> persons = childByAddress.listOfChildren(personsWithoutAge);

		response = childAlertView.formateChildAlert(persons);

		return response;

	}

	/**
	 * Retourne une liste des numéros de téléphone des résidents desservis par la caserne de pompiers spécifiée.
	 * 
	 * @param station numéro de la caserne de pompier visée.
	 * @return json de numéro de téléphone des personnes couvertes par la caserne spécifiée.
	 */
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

	/**
	 * Retourne une liste de tous les foyers desservis par les casernes spécifiées.
	 * 
	 * @param stationNumbers liste des numéros de caserne visées.
	 * @return json des adresses couvertes avec les habitants (nom, numéro de téléphone, age et dossier medical).
	 */
	@GetMapping("/flood/stations")
	public Map<String, Object> getPersonsByStations(@RequestParam("stationNumber") List<Long> stationNumbers) {
		Map<String, Object> response = new HashMap<>();

		List<String> coveredAddresses = floodStationService.getCoveredAddresses(stationNumbers);

		response = floodStationView.formatFloodStation(coveredAddresses,
				floodStationService.getPersonsData(coveredAddresses));

		return response;
	}

}
