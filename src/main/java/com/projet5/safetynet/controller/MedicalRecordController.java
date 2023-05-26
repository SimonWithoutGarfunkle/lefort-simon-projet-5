package com.projet5.safetynet.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projet5.safetynet.model.MedicalRecord;
import com.projet5.safetynet.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	
    @Autowired
    private MedicalRecordService medicalRecordService;

    /**
    * Read - Get all MedicalRecords
    * @return - An Iterable object of Medical Record full filled
    */
    @GetMapping("/medicalrecords")
    public Iterable<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.getMedicalRecords();
    }
    
    @GetMapping("/medicalrecords/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordService.getMedicalRecord(id);

        if (optionalMedicalRecord.isPresent()) {
            MedicalRecord medicalRecord = optionalMedicalRecord.get();
            return ResponseEntity.ok(medicalRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
