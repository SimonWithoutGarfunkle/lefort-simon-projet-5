package com.safetynetjson.safetynetjson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;
import com.safetynetjson.safetynetjson.service.ChildByAddress;
import com.safetynetjson.safetynetjson.service.CommunityEmail;
import com.safetynetjson.safetynetjson.service.FireService;
import com.safetynetjson.safetynetjson.service.FloodStationService;
import com.safetynetjson.safetynetjson.service.PersonInfo;
import com.safetynetjson.safetynetjson.service.PersonService;
import com.safetynetjson.safetynetjson.service.PersonsByStationNumber;
import com.safetynetjson.safetynetjson.vue.ChildAlertView;
import com.safetynetjson.safetynetjson.vue.FloodStationView;

import jakarta.validation.Valid;

/**
 * expose les GET pour les requetes complexes.
 * 
 * @author Simon
 *
 */
@Validated
@RestController
public class AlertController {

	private final PersonService personService;

	private final ChildByAddress childByAddress;

	private final ChildAlertView childAlertView;

	private final PersonsByStationNumber personsByStationNumber;

	private final FloodStationService floodStationService;

	private final FloodStationView floodStationView;
	
	private final FireService fireService;
	
	private final CommunityEmail communityEmail;
	
	private final PersonInfo personInfo;
	
	private static final Logger logger = LogManager.getLogger(AlertController.class);

	public AlertController(PersonService personService, ChildByAddress childByAddress, ChildAlertView childAlertView,
			PersonsByStationNumber personsByStationNumber, 
			FloodStationService floodStationService, FloodStationView floodStationView,
			FireService fireService, CommunityEmail communityEmail, PersonInfo personInfo) {
		
		this.personService = personService;
		this.childByAddress = childByAddress;
		this.childAlertView = childAlertView;
		this.personsByStationNumber = personsByStationNumber;
		this.floodStationService = floodStationService;
		this.floodStationView = floodStationView;
		this.fireService = fireService;
		this.communityEmail = communityEmail;
		this.personInfo = personInfo;
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
	public Map<String, Object> getChildByAddress(@Valid @RequestParam("address") String address) {
		logger.info("Récupération des enfants habitant l'adresse " + address);
		Map<String, Object> response = new HashMap<>();

		List<Person> personsWithoutAge = personService.getPersonsByAddress(address);

		List<PersonWithMedicalrecord> persons = childByAddress.listOfChildren(personsWithoutAge);

		response = childAlertView.formateChildAlert(persons);
		logger.info(response);

		return response;

	}

	/**
	 * Retourne une liste des numéros de téléphone des résidents desservis par la caserne de pompiers spécifiée.
	 * 
	 * @param station numéro de la caserne de pompier visée.
	 * @return json de numéro de téléphone des personnes couvertes par la caserne spécifiée.
	 */
	@GetMapping("/phoneAlert")
	public Map<String, List<String>> getPhoneByStationNumber(@Valid @RequestParam("station") Long station) {

		logger.info("Récupération des numéros de téléphone des résidents couverts par la caserne " + station);
		Map<String, List<String>> response = new HashMap<>();

		List<Person> persons = personsByStationNumber.listOfPersonsByStation(station);

		List<String> phoneNumbers = new ArrayList<>();

		for (Person person : persons) {
			String phoneNumber = person.getPhone();

			//Suppression des doublons
			if (!phoneNumbers.contains(phoneNumber)) {
				phoneNumbers.add(phoneNumber);
			}
		}

		response.put("phone", phoneNumbers);
		logger.info(response);
		return response;

	}

	/**
	 * Retourne une liste de tous les foyers desservis par les casernes spécifiées.
	 * 
	 * @param stationNumbers liste des numéros de caserne visées.
	 * @return json des adresses couvertes avec les habitants (nom, numéro de téléphone, age et dossier medical).
	 */
	@GetMapping("/flood/stations")
	public Map<String, Object> getPersonsByStations(@Valid @RequestParam("stationNumber") List<Long> stationNumbers) {
		logger.info("Récupération des foyers desservis par les casernes spécifiées");
		Map<String, Object> response = new HashMap<>();

		List<String> coveredAddresses = floodStationService.getCoveredAddresses(stationNumbers);

		response = floodStationView.formatFloodStation(coveredAddresses,
				floodStationService.getPersonsData(coveredAddresses));

		logger.info(response);
		return response;
	}
	
	/**
	 * Retourne la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne de pompiers la desservant
	 * 
	 * @param address 
	 * @return Json des habitants avec la caserne concernée, le nom, le numéro de téléphone, l'âge et les antécédents médicaux (médicaments, posologie et allergies)
	 */
	@GetMapping("/fire")
	public List<Map<String, Object>> getPersonsByAddress(@Valid @RequestParam("address") String address) {
		logger.info("Récupération des habitants à l'adresse " + address);
		List<Map<String, Object>> response = fireService.getPersonsForFire(address);
		logger.info(response);
		return response;
		
	}
	
	/**
	 * Retourne la liste des emails des habitants de la ville spécifiée sans doublon
	 * 
	 * @param city
	 * @return Json des emails des habitants
	 */
	@GetMapping("/communityEmail")
	public List<String> getEmailOfCity(@Valid @RequestParam("city") String city){
		logger.info("Récupération des email de la ville " + city);
		List<String> response = communityEmail.getCommunityEmail(city);
		logger.info(response);
		return response;
		
	}
	
	
	/**
	 * Retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments, posologie, allergies) de la personne.
	 * Si plusieurs personnes portent le même nom et prénom, elles doivent toutes apparaître.
	 * Attention, cette méthode a été faite pour l'exercice mais ne doit pas etre utilisée en cas de doublons nom/prénom car le rapprochement avec le dossier medical ne serait pas fiable
	 * 
	 * @param firstName
	 * @param lastName
	 * @return Json avec les infos de toutes les personnes qui correspondent au nom et prénom spécifiés
	 */
	@GetMapping("/personInfo")
	public List<PersonWithMedicalrecord> getInfoPerson(@Valid @RequestParam String firstName, @RequestParam String lastName) {	
		logger.info("Récupération du dossier medical de "+ firstName +" "+ lastName);
		List<PersonWithMedicalrecord> response =personInfo.getPersonInfo(firstName, lastName);
		logger.info(response);
		return response;
		
	}
	
	

}
