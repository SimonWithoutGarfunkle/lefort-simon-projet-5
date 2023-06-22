package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger logger = LogManager.getLogger(ChildByAddress.class);
	
	public ChildByAddress(PersonWithMedicalrecordService personWithAgeService) {
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
		//S'il n'y a aucun enfant dans la liste, il faut retourner une liste vide
		if (countChildren==0) {
			logger.info("Aucun enfant dans la liste, suppression du resultat");
			result.clear();
			
		}
								
		return result;
	}

}
