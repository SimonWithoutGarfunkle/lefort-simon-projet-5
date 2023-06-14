package com.safetynetjson.safetynetjson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;
import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.service.FirestationService;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.PersonWithMedicalrecordService;
import com.safetynetjson.safetynetjson.service.PersonsByStationNumber;

import jakarta.validation.Valid;

/**
 * Implemente le CRUD pour la table FireStation (caserne de pompiers)
 * Cette table contient la liste des adresse de la zone géographique et le n° de la caserne attribuée a chaque adresse
 * @author Simon
 *
 */
@RestController
@Validated
public class FirestationController {
	
	private final JsonDataService jsonDataService;
	
	private final FirestationService firestationService;
	
    private final PersonsByStationNumber personsByStationNumber;
    
    private final PersonWithMedicalrecordService personWithAgeService;
    
    private static Logger logger = LoggerFactory.getLogger(FirestationController.class);
	
	public FirestationController(JsonDataService jsonDataService,
			FirestationService firestationService, PersonsByStationNumber personsByStationNumber, 
			PersonWithMedicalrecordService personWithAgeService) {
        this.jsonDataService = jsonDataService;
        this.firestationService = firestationService;
        this.personsByStationNumber = personsByStationNumber;
        this.personWithAgeService = personWithAgeService;
    }
	
	
	/**
	 * Retourne toutes les correspondences adresse et n° de caserne
	 * 
	 * @return json des n° de casernes et des adresses couvertes
	 */
	@GetMapping("/firestations")
	public ResponseEntity<List<Firestation>> getAllFirestations() {
		logger.info("Recuperation de toutes les casernes");
		
		JsonData jsonData = jsonDataService.getJsonData();

		List<Firestation> firestations = jsonData.getFirestations();
	    
	    return ResponseEntity.ok(firestations);
	}
	
	
	
	/**
	 * Ajoute le mapping adresse/n° de caserne à la base de données
	 * 
	 * @param firestation adresse et n° de caserne qui la couvre
	 * @return code de statut de réponse HTTP, 201 attendu
	 */
	@PostMapping("/firestation")    
	public ResponseEntity<String> postFirestation(@Valid @RequestBody Firestation firestation) {
		logger.info("Ajout de la caserne "+firestation.getAddress());
		if (firestationService.addressPresent(firestation)) {
			logger.error("Adresse déjà enregistrée dans le système");
			return ResponseEntity.badRequest().body("Address already registered");
		}
		firestationService.addFirestation(firestation);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Firestation added successfully");
    }
	
	/**
	 * Modifie la caserne attribuée à l'adresse indiquée
	 * 
	 * @param firestation adresse et n° de caserne qui la couvre
	 * @return code de statut de réponse HTTP, 200 attendu
	 */
	@PutMapping("/firestation")
    public ResponseEntity<String> putFirestation(@Valid @RequestBody Firestation firestation) {
		logger.info("Mise à jour de la caserne "+firestation.getAddress());
		if (!(firestationService.addressPresent(firestation))) {
			logger.error("Aucune caserne enregistrée à cette adresse");
			return ResponseEntity.badRequest().body("Firestation not found");
		}
		firestationService.updateFirestation(firestation);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Firestation station updated successfully");
    }
	
	/**
	 * Si le parametre contient un mapping adresse/n° de caserne correct, le supprime de la base
	 * Si le parametre ne contient qu'une adresse, supprime le mapping (n° de caserne) pour l'adresse indiquée
	 * 
	 * @param firestation
	 * @return String qui précise l'action effectuée (failed, mapping removed ou firestation removed)
	 */
	@DeleteMapping("/firestation")
    public ResponseEntity<String> deleteFirestation(@Valid @RequestBody Firestation firestation) {
		logger.info("Suppresion de la caserne "+firestation.getAddress());
		if (!(firestationService.addressPresent(firestation))) {
			logger.error("Aucune caserne enregistrée à cette adresse");
			return ResponseEntity.badRequest().body("Firestation not found");
		}
		String result = firestationService.removeFirestation(firestation);
		logger.info(result);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
	
	/**
	 * Retourne la liste des personnes couvertes par le n° de caserne spécifié ainsi qu'un décompte du nombre de personnes et d'enfants 
	 * @param stationNumber
	 * @return Json avec la liste des personnes couvertes (nom complet, adresse et téléphone) ainsi que le décompte de personne et d'enfant dans cette liste
	 */
	@GetMapping("/firestation")
    public Map<String, Object> getPersonsByStation(@Valid @RequestParam("stationNumber") Long stationNumber) {
		logger.info("Récuperation des personnes couvertes par la station " + stationNumber);
		Map<String, Object> response = new HashMap<>();
        
        List<Person> personsWithoutAge = personsByStationNumber.listOfPersonsByStation(stationNumber);
        
        List<PersonWithMedicalrecord> persons = personWithAgeService.addAgeToPersons(personsWithoutAge);
        

        List<Map<String, Object>> simplifiedPersons = new ArrayList<>();
        for (Person person : personsWithoutAge) {
            Map<String, Object> simplifiedPerson = new HashMap<>();
            simplifiedPerson.put("firstName", person.getFirstName());
            simplifiedPerson.put("lastName", person.getLastName());
            simplifiedPerson.put("address", person.getAddress());
            simplifiedPerson.put("phoneNumber", person.getPhone());
            simplifiedPersons.add(simplifiedPerson);
        }

        logger.info("Ajout du décompte de personnes à la liste");
        response.put("persons", simplifiedPersons);
        response.put("personCount", personWithAgeService.countPeople(persons));
        response.put("childrenCount", personWithAgeService.countChild(persons));

        return response;
    }
    


}
