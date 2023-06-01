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

import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.MedicalrecordService;

@RestController
public class MedicalrecordController {
	
	
	private final JsonDataService jsonDataService;
	
	private final MedicalrecordService medicalrecordService;
	
	public MedicalrecordController(JsonDataService jsonDataService, MedicalrecordService medicalrecordService) {
        this.jsonDataService = jsonDataService;
        this.medicalrecordService = medicalrecordService;
    }
	
	@GetMapping("/medicalrecords")
	public ResponseEntity<List<Medicalrecord>> getAllMedicalrecords() {
		
		JsonData jsonData = jsonDataService.getJsonData();

		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();
	    
	    return ResponseEntity.ok(medicalrecords);
	}
	
	@PostMapping("/medicalrecord")
    public ResponseEntity<String> postMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (medicalrecordService.isPresent(medicalrecord)) {
			return ResponseEntity.badRequest().body("Medicalrecord already exists");
		}
		medicalrecordService.addMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Medicalrecord added successfully");
    }
	
	@PutMapping("/medicalrecord")
    public ResponseEntity<String> putMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (!(medicalrecordService.isPresent(medicalrecord))) {
			return ResponseEntity.badRequest().body("Medicalrecord not found");
		}
		medicalrecordService.updateMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Medicalrecord updated successfully");
    }
	
	@PatchMapping("/medicalrecord")
    public ResponseEntity<String> patchMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (!(medicalrecordService.isPresent(medicalrecord))) {
			return ResponseEntity.badRequest().body("Medicalrecord not found");
		}
		medicalrecordService.patchMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Medicalrecord updated successfully");
    }
	
	@DeleteMapping("/medicalrecord")
    public ResponseEntity<String> deleteMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (!(medicalrecordService.isPresent(medicalrecord))) {
			return ResponseEntity.badRequest().body("Medicalrecord not found");
		}
		medicalrecordService.removeMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Medicalrecord removed successfully");
    }

}
