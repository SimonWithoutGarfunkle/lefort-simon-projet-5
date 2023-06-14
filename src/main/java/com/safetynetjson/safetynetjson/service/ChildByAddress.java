package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;

/**
 * Service dédié à la requete ChildAlert qui retourne une liste d'enfant à partir d'une adresse
 * 
 * @author Simon
 *
 */
@Service
public class ChildByAddress {

	private final PersonWithMedicalrecordService personWithAgeService;
	
    private static Logger logger = LoggerFactory.getLogger(ChildByAddress.class);
	
	public ChildByAddress(JsonDataService jsonDataService, PersonService personService,
			PersonWithMedicalrecordService personWithAgeService) {
		this.personWithAgeService = personWithAgeService;
	}

	/**
	 * Ajoute l'age à chaque personne de la liste puis vérifie qu'il y a au moins 1 enfant dans la liste
	 * 
	 * @param persons
	 * @return liste des memes personnes enrichies avec l'age
	 */
	public List<PersonWithMedicalrecord> listOfChildren (List<Person> persons) {
		logger.info("Ajout de l'age aux personnes listees");
		List<PersonWithMedicalrecord> result = new ArrayList<PersonWithMedicalrecord>();
		result = personWithAgeService.addAgeToPersons(persons);
		int countChildren = 0;
		for (PersonWithMedicalrecord someone : result) {
			if (someone.getAge() <= 18) {
				countChildren +=1;
			}
			
		}
		if (countChildren==0) {
			logger.info("Aucun enfant dans la liste, suppression du resultat");
			result.clear();
			
		}
								
		return result;
	}

}
