package com.safetynetjson.safetynetjson.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
public class PersonController {
	
	private final JsonDataService jsonDataService;
	
	private final PersonService personService;
	
	public PersonController(JsonDataService jsonDataService, PersonService personService) {
        this.jsonDataService = jsonDataService;
        this.personService = personService;
    }
	
	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPersons() {
		
		JsonData jsonData = jsonDataService.getJsonData();

		List<Person> persons = jsonData.getPersons();
	    
	    return ResponseEntity.ok(persons);
	}
	
	
	@PostMapping("/person")
    public ResponseEntity<String> postPerson(@RequestBody Person person) {
		if (personService.isPresent(person)) {
			return ResponseEntity.badRequest().body("Person already exists");
		}
		personService.addPerson(person);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Person added successfully");
    }
	
	@PutMapping("/person")
    public ResponseEntity<String> putPerson(@RequestBody Person person) {
		if (!(personService.isPresent(person))) {
			return ResponseEntity.badRequest().body("Person not found");
		}
		personService.updatePerson(person);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Person updated successfully");
    }
	
	@PatchMapping("/person")
    public ResponseEntity<String> patchPerson(@RequestBody Person person) {
		if (!(personService.isPresent(person))) {
			return ResponseEntity.badRequest().body("Person not found");
		}
		personService.patchPerson(person);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Person updated successfully");
    }
	
	@DeleteMapping("/person")
    public ResponseEntity<String> deletePerson(@RequestBody Person person) {
		if (!(personService.isPresent(person))) {
			return ResponseEntity.badRequest().body("Person not found");
		}
		personService.removePerson(person);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Person removed successfully");
    }
    

}
