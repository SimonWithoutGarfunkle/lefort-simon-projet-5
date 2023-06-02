package com.safetynetjson.safetynetjson.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithAge;
import com.safetynetjson.safetynetjson.service.ChildByAddress;
import com.safetynetjson.safetynetjson.service.PersonService;
import com.safetynetjson.safetynetjson.vue.ChildAlertView;

@RestController
public class AlertController {
	
	private final PersonService personService;
	
	private final ChildByAddress childByAddress;
	
	private final ChildAlertView childAlertView;
	
	public AlertController(PersonService personService, ChildByAddress childByAddress, 
			ChildAlertView childAlertView) {
		this.personService = personService;
		this.childByAddress = childByAddress;
		this.childAlertView = childAlertView;
	}

	@GetMapping("/childAlert")
    public Map<String, Object> getChildByAddress(@RequestParam("address") String address) {
        Map<String, Object> response = new HashMap<>();
        
        List<Person> personsWithoutAge = personService.getPersonsByAddress(address);
        
        List<PersonWithAge> persons = childByAddress.listOfChildren(personsWithoutAge);
        
        response = childAlertView.formateChildAlert(persons);
        
        return response;
        

    }
	

}
