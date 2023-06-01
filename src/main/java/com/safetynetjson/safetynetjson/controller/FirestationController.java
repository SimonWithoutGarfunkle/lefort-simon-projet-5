package com.safetynetjson.safetynetjson.controller;

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
import com.safetynetjson.safetynetjson.model.PersonWithAge;
import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.service.FirestationService;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.PersonsByStationNumber;
import com.safetynetjson.safetynetjson.service.FirestationService;

@RestController
public class FirestationController {
	
	private final JsonDataService jsonDataService;
	
	private final FirestationService firestationService;
	
    private final PersonsByStationNumber personsByStationNumber;
	
	public FirestationController(JsonDataService jsonDataService, FirestationService firestationService, PersonsByStationNumber personsByStationNumber) {
        this.jsonDataService = jsonDataService;
        this.firestationService = firestationService;
        this.personsByStationNumber = personsByStationNumber;
    }
	
	@GetMapping("/firestations")
	public ResponseEntity<List<Firestation>> getAllFirestations() {
		
		JsonData jsonData = jsonDataService.getJsonData();

		List<Firestation> firestations = jsonData.getFirestations();
	    
	    return ResponseEntity.ok(firestations);
	}
	
	
	@PostMapping("/firestation")
    public ResponseEntity<String> postFirestation(@RequestBody Firestation firestation) {
		if (firestationService.addressPresent(firestation)) {
			return ResponseEntity.badRequest().body("Address already registered");
		}
		firestationService.addFirestation(firestation);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Firestation added successfully");
    }
	
	@PutMapping("/firestation")
    public ResponseEntity<String> putFirestation(@RequestBody Firestation firestation) {
		if (!(firestationService.addressPresent(firestation))) {
			return ResponseEntity.badRequest().body("Firestation not found");
		}
		firestationService.updateFirestation(firestation);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Firestation station updated successfully");
    }
	
	@DeleteMapping("/firestation")
    public ResponseEntity<String> deleteFirestation(@RequestBody Firestation firestation) {
		if (!(firestationService.addressPresent(firestation))) {
			return ResponseEntity.badRequest().body("Firestation not found");
		}
		String result = firestationService.removeFirestation(firestation);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
	
	@GetMapping("/firestation")
    public Map<String, Object> getPersonsByStation(@RequestParam("stationNumber") Long stationNumber) {
        Map<String, Object> response = new HashMap<>();
        
        List<Person> personsWithoutAge = personsByStationNumber.listOfPersonsByStation(stationNumber);
        
        List<PersonWithAge> persons = personsByStationNumber.listOfPersonsByStationWithAge(personsWithoutAge);
        

        response.put("persons", persons);
        response.put("personCount", personsByStationNumber.countPeople(persons));
        response.put("childrenCount", personsByStationNumber.countChild(persons));

        return response;
    }
    


}
