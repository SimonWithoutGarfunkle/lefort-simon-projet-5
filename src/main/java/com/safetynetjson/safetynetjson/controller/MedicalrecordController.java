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
import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.MedicalrecordService;

/**
 *  Implemente le CRUD pour la table Medicalrecord (dossier medical)
 *  Cette table contient les noms, date de naissances, allergies et traitements des personnes enregistrées
 * @author Simon
 *
 */
@RestController
public class MedicalrecordController {
	
	
	private final JsonDataService jsonDataService;
	
	private final MedicalrecordService medicalrecordService;
	
	public MedicalrecordController(JsonDataService jsonDataService, MedicalrecordService medicalrecordService) {
        this.jsonDataService = jsonDataService;
        this.medicalrecordService = medicalrecordService;
    }
	
	/**
	 * Retourne tous les dossiers medicaux de la base
	 * 
	 * @return Json avec tous les dossiers medicaux
	 */
	@GetMapping("/medicalrecords")
	public ResponseEntity<List<Medicalrecord>> getAllMedicalrecords() {
		
		JsonData jsonData = jsonDataService.getJsonData();

		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();
	    
	    return ResponseEntity.ok(medicalrecords);
	}
	
	/**
	 * Ajoute le dossier spécifié à la base
	 * 
	 * @param medicalrecord
	 * @return code de statut de réponse HTTP, 201 attendu
	 */
	@PostMapping("/medicalrecord")
    public ResponseEntity<String> postMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (medicalrecordService.isPresent(medicalrecord)) {
			return ResponseEntity.badRequest().body("Medicalrecord already exists");
		}
		medicalrecordService.addMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Medicalrecord added successfully");
    }
	
	/**
	 * Met à jour la date de naissance, traitements et allergies du dossier spécifié
	 * Le dossier est identifié avec le nom et le prénom
	 * 
	 * @param medicalrecord
	 * @return code de statut de réponse HTTP, 200 attendu
	 */
	@PutMapping("/medicalrecord")
    public ResponseEntity<String> putMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (!(medicalrecordService.isPresent(medicalrecord))) {
			return ResponseEntity.badRequest().body("Medicalrecord not found");
		}
		medicalrecordService.updateMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Medicalrecord updated successfully");
    }
	
	/**
	 * Mise à jour partiel du dossier spécifié avec uniquement les champs précisés parmi (date de naissance, traitements et allergies)
	 * Le dossier est identifié avec le nom et le prénom
	 * 
	 * @param medicalrecord
	 * @return code de statut de réponse HTTP, 201 attendu
	 */
	@PatchMapping("/medicalrecord")
    public ResponseEntity<String> patchMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (!(medicalrecordService.isPresent(medicalrecord))) {
			return ResponseEntity.badRequest().body("Medicalrecord not found");
		}
		medicalrecordService.patchMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("FirstName and LastName cant be modified. Medicalrecord updated successfully");
    }
	
	/**
	 * Supprime tout le dossier spécifié de la base
	 * Le dossier est identifié avec le nom et le prénom
	 * 
	 * @param medicalrecord
	 * @return code de statut de réponse HTTP, 201 attendu
	 */
	@DeleteMapping("/medicalrecord")
    public ResponseEntity<String> deleteMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		if (!(medicalrecordService.isPresent(medicalrecord))) {
			return ResponseEntity.badRequest().body("Medicalrecord not found");
		}
		medicalrecordService.removeMedicalrecord(medicalrecord);
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Medicalrecord removed successfully");
    }

}
