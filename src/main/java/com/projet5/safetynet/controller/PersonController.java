package com.projet5.safetynet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet5.safetynet.model.Person;
import com.projet5.safetynet.service.PersonService;

@RestController
public class PersonController {
	
    @Autowired
    private PersonService personService;

    /**
    * Read - Get all persons
    * @return - An Iterable object of Person full filled
    */
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }

}
