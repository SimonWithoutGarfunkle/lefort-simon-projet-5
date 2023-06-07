package com.safetynetjson.safetynetjson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.safetynetjson.safetynetjson.service.PersonWithMedicalRecord;
import com.safetynetjson.safetynetjson.service.PersonsByStationNumber;

/**
 * Implemente le CRUD pour la table FireStation (caserne de pompiers)
 * Cette table contient la liste des adresse de la zone géographique et le n° de la caserne attribuée a chaque adresse
 * @author Simon
 *
 */
@RestController
public class FirestationController {
	
	private final JsonDataService jsonDataService;
	
	private final FirestationService firestationService;
	
    private final PersonsByStationNumber personsByStationNumber;
    private final PersonWithMedicalRecord personWithAgeService;
	
	public FirestationController(JsonDataService jsonDataService,
			FirestationService firestationService, PersonsByStationNumber personsByStationNumber, 
			PersonWithMedicalRecord personWithAgeService) {
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
	public ResponseEntity<String> postFirestation(@RequestBody Firestation firestation) {
		if (firestationService.addressPresent(firestation)) {
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
    public ResponseEntity<String> putFirestation(@RequestBody Firestation firestation) {
		if (!(firestationService.addressPresent(firestation))) {
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
    public ResponseEntity<String> deleteFirestation(@RequestBody Firestation firestation) {
		if (!(firestationService.addressPresent(firestation))) {
			return ResponseEntity.badRequest().body("Firestation not found");
		}
		String result = firestationService.removeFirestation(firestation);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
	
	/**
	 * Retourne la liste des personnes couvertes par le n° de caserne spécifié ainsi qu'un décompte du nombre de personnes et d'enfants 
	 * @param stationNumber
	 * @return Json avec la liste des personnes couvertes (nom complet, adresse et téléphone) ainsi que le décompte de personne et d'enfant dans cette liste
	 */
	@GetMapping("/firestation")
    public Map<String, Object> getPersonsByStation(@RequestParam("stationNumber") Long stationNumber) {
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

        response.put("persons", simplifiedPersons);
        response.put("personCount", personWithAgeService.countPeople(persons));
        response.put("childrenCount", personWithAgeService.countChild(persons));

        return response;
    }
    


}
