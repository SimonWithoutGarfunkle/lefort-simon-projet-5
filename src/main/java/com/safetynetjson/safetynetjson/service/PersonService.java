package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;

/**
 * Service dédié au CRUD et autres requetes basiques pour la table Person (personnes)
 * @author Simon
 *
 */
@Service
public class PersonService {

	private final JsonDataService jsonDataService;
	private static final Logger logger = LogManager.getLogger(PersonService.class);


	public PersonService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	/**
	 * Retourne toutes les personnes enregistrées
	 * @return Liste de toutes les personnes du systeme
	 */
	public List<Person> getPersons() {
		logger.info("Recuperation de toutes les personnes");
		JsonData jsonData = jsonDataService.getJsonData();
		return jsonData.getPersons();
	}

	/**
	 * Verifie si la personne spécifiée est deja présente dans la base
	 * @param person
	 * @return boolean vrai s'il y a deja une personne enregistrée avec le meme nom et meme prénom
	 */
	public boolean isPresent(Person person) {
		logger.info("Verification de la presence dans la base de "+person.getFirstName()+" "+person.getLastName());
		List<Person> persons = getPersons();
		for (Person someone : persons) {
			if (someone.getFirstName().equals(person.getFirstName())
					&& (someone.getLastName().equals(person.getLastName()))) {
				logger.info("Personne trouvee");
				return true;
			}
		}

		logger.error("Personne introuvable");
		return false;

	}

	/**
	 * Vérifie que la personne spécifiée est présente en base et la renvoie
	 * 
	 * @param person
	 * @return personne de la base avec le meme nom et meme prénom
	 */
	public Person returnPersonFromBase(Person person) {
		logger.info("Recuperation dans la base de "+person.getFirstName()+" "+person.getLastName());
		List<Person> persons = getPersons();
		if (isPresent(person)) {
			for (Person someone : persons) {
				if (someone.getFirstName().equals(person.getFirstName())
						&& (someone.getLastName().equals(person.getLastName()))) {
					return someone;
				}
			}

		}
		return null;

	}

	/**
	 * Ajoute la personne spécifiée à la base de données
	 * @param person
	 */
	public void addPerson(Person person) {
		logger.info("Ajout dans la base de "+person.getFirstName()+" "+person.getLastName());
		List<Person> persons = getPersons();
		persons.add(person);

	}

	/**
	 * Supprime la personne spécifiée de la base de données
	 * @param person
	 */
	public void removePerson(Person person) {
		logger.info("Suppresion de la base de "+person.getFirstName()+" "+person.getLastName());
		Person personToRemove = returnPersonFromBase(person);
		List<Person> persons = getPersons();
		persons.remove(personToRemove);

	}

	/**
	 * Mise à jour de la personne spécifiée
	 * @param person
	 */
	public void updatePerson(Person person) {
		logger.info("Mise a jour de "+person.getFirstName()+" "+person.getLastName());
		Person personToUpdate = returnPersonFromBase(person);
		personToUpdate.setAddress(person.getAddress());
		personToUpdate.setCity(person.getCity());
		personToUpdate.setZip(person.getZip());
		personToUpdate.setPhone(person.getPhone());
		personToUpdate.setEmail(person.getEmail());

	}

	/**
	 * Mise à jour partielle de la personne spécifiée
	 * @param person
	 */
	public void patchPerson(Person person) {
		logger.info("Mise a jour partielle de "+person.getFirstName()+" "+person.getLastName());
		Person personToUpdate = returnPersonFromBase(person);
		if (!(person.getAddress() == null)) {
			logger.info("Mise a jour de l'adresse");
			personToUpdate.setAddress(person.getAddress());
		}
		if (!(person.getCity() == null)) {
			logger.info("Mise a jour de la ville");
			personToUpdate.setCity(person.getCity());
		}
		if (!(person.getZip() == null)) {
			logger.info("Mise a jour du code postal");
			personToUpdate.setZip(person.getZip());

		}
		if (!(person.getPhone() == null)) {
			logger.info("Mise a jour du telephonee");
			personToUpdate.setPhone(person.getPhone());
		}
		if (!(person.getEmail() == null)) {
			logger.info("Mise a jour de l'email");
			personToUpdate.setEmail(person.getEmail());

		}

	}

	/**
	 * Retourne toutes les personnes habitants l'adresse indiquée
	 * 
	 * @param address
	 * @return Liste des personnes habitants l'adresse indiquée
	 */
	public List<Person> getPersonsByAddress(String address) {
		logger.info("Recuperation des personnes habitants a "+address);
		List<Person> persons = getPersons();
		List<Person> result = new ArrayList<Person>();
		for (Person someone : persons) {
			if (someone.getAddress().equals(address)) {
				result.add(someone);

			}
		}
		return result;
	}

}
