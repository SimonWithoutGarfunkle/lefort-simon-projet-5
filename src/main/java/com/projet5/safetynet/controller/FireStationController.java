package com.projet5.safetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet5.safetynet.model.FireStation;
import com.projet5.safetynet.service.FireStationService;

@RestController
public class FireStationController {
	
    @Autowired
    private FireStationService fireStationService;

    /**
    * Read - Get all fire stations
    * @return - An Iterable object of fire station full filled
    */
    @GetMapping("/firestations")
    public Iterable<FireStation> getFireStations() {
        return fireStationService.getFireStations();
    }

}
