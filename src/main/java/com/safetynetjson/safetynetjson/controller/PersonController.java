package com.safetynetjson.safetynetjson.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.PersonService;

import jakarta.validation.Valid;

/**
 * Implemente le CRUD pour la table Person (les habitants)
 * Cette table contient la liste des habitants avec les noms, adresse et coordonnées
 * @author Simon
 *
 */
@Validated
@RestController
public class PersonController {
	
	private final JsonDataService jsonDataService;
	
	private final PersonService personService;
	
    private static Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	public PersonController(JsonDataService jsonDataService, PersonService personService) {
        this.jsonDataService = jsonDataService;
        this.personService = personService;
    }
	
	/**
	 * Retourne toutes les personnes de la base
	 * 
	 * @return json avec les noms, prénoms, adresses completes, email et telephone
	 */
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		logger.info("Recuperation de toutes les personnes");
		JsonData jsonData = jsonDataService.getJsonData();

		List<Person> persons = jsonData.getPersons();
	    
	    return ResponseEntity.ok(persons);
	}
	
	
	/**
	 * Ajoute la personne spécifiée à la base
	 * 
	 * @param person Personne avec nom, prénom, adresse completes email et telephone
	 * @return code de statut de réponse HTTP, 201 attendu
	 */
	@PostMapping("/person")
    public ResponseEntity<String> postPerson(@Valid @RequestBody Person person) {
		logger.info("Ajout a la liste de "+ person.getFirstName()+" "+person.getLastName());
		if (personService.isPresent(person)) {
			logger.error("Personne deja enregistree");
			return ResponseEntity.badRequest().body("Person already exists");
		}
		personService.addPerson(person);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Person added successfully");
    }
	
	/**
	 * Met à jour les champs de la personne spécifiée : adresse completes email et telephone
	 * La personne est identifiée avec son nom et son prénom
	 * 
	 * @param person
	 * @return code de statut de réponse HTTP, 200 attendu
	 */
	@PutMapping("/person")
	public ResponseEntity<String> putPerson(@Valid @RequestBody Person person) {
		logger.info("Mise a jour de "+ person.getFirstName()+" "+person.getLastName());
		if (!(personService.isPresent(person))) {
			logger.error("Personne introuvable");
			return ResponseEntity.badRequest().body("Person not found");
		}
		personService.updatePerson(person);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Person updated successfully");
    }
	
	/**
	 * Mise à jour partiel de la personne spécifiée avec uniquement les champs précisés parmi (adresse completes email et telephone)
	 * La personne est identifiée avec son nom et son prénom
	 * 
	 * @param person
	 * @return code de statut de réponse HTTP, 200 attendu
	 */
	@PatchMapping("/person")
    public ResponseEntity<String> patchPerson(@Valid @RequestBody Person person) {
		logger.info("Mise a jour partielle de "+ person.getFirstName()+" "+person.getLastName());
		if (!(personService.isPresent(person))) {
			logger.error("Personne introuvable");
			return ResponseEntity.badRequest().body("Person not found");
		}
		personService.patchPerson(person);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Person updated successfully");
    }
	
	/**
	 * Supprime la personne spécifiée de la base
	 * La personne est identifiée avec son nom et son prénom
	 * 
	 * @param person
	 * @return code de statut de réponse HTTP, 200 attendu
	 */
	@DeleteMapping("/person")
    public ResponseEntity<String> deletePerson(@Valid @RequestBody Person person) {
		logger.info("Suppresion de "+ person.getFirstName()+" "+person.getLastName());
		if (!(personService.isPresent(person))) {
			logger.error("Personne introuvable");
			return ResponseEntity.badRequest().body("Person not found");
		}
		personService.removePerson(person);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Person removed successfully");
    }
    

}
