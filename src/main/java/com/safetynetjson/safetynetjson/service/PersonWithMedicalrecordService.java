package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;

/**
 * Opérations liées à l'age des personnes
 * @author Simon
 *
 */
@Service
public class PersonWithMedicalrecordService {

	private final MedicalrecordService medicalrecordService;
	
	private static final Logger logger = LogManager.getLogger(PersonWithMedicalrecordService.class);


	public PersonWithMedicalrecordService(MedicalrecordService medicalrecordService) {
		this.medicalrecordService = medicalrecordService;
	}

	/**
	 * Verifie la majorité de la personne
	 * 
	 * @param person
	 * @return boolean vrai si la personne a 18 ans ou moins
	 */
	public boolean isAChild(Person person) {
		logger.info("Verification de la majorité de "+person.getFirstName()+" "+person.getLastName());
		if (medicalrecordService.getAge(person) > 18) {
			return false;
		}
		return true;

	}

	/**
	 * Complete la liste de personnes spécifiées en ajoutant leur age
	 * 
	 * @param persons
	 * @return liste de personnes enrichie avec leur age
	 */
	public List<PersonWithMedicalrecord> addAgeToPersons(List<Person> persons) {
		logger.info("Ajout des ages a la liste de personne");
		List<PersonWithMedicalrecord> result = new ArrayList<PersonWithMedicalrecord>();
		for (Person someone : persons) {
			result.add(new PersonWithMedicalrecord(someone, medicalrecordService.getAge(someone)));
		}

		return result;
	}
	
	/**
	 * Compte le nombre d'enfant dans la liste
	 * @param personsWithAge
	 * @return int nombre d'enfant dans la liste spécifiée
	 */
	public int countChild(List<PersonWithMedicalrecord> personsWithAge) {
		logger.info("Decompte du nombre d'enfant dans la liste");
		int child = 0;
		for (PersonWithMedicalrecord someone : personsWithAge) {
			if (someone.getAge() <= 18 ) {
				child += 1;
				
			} 
		}
		return child;
		
	}
	
	/**
	 * Compte le nombre de personne dans la liste
	 * 
	 * @param personsWithAge
	 * @return int nombre de personne dans la liste spécifiée
	 */
	public int countPeople(List<PersonWithMedicalrecord> personsWithAge) {
		logger.info("Decompte du nombre de personne dans la liste");
		return personsWithAge.size();
		
	}
	

}
