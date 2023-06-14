package com.safetynetjson.safetynetjson.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Person;

/**
 * Service dédié au CRUD et autres requetes basiques pour la table Medicalrecord (dossier medical)
 * @author Simon
 *
 */
@Service
public class MedicalrecordService {

	private final JsonDataService jsonDataService;
	
	private static Logger logger = LoggerFactory.getLogger(MedicalrecordService.class);

	public MedicalrecordService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	/**
	 * Retourne tous les dossiers medicaux de la base de données
	 * @return
	 */
	private List<Medicalrecord> getRecords() {
		logger.info("Recuperation de tous les dossiers medicaux");
		JsonData jsonData = jsonDataService.getJsonData();
		return jsonData.getMedicalrecords();

	}

	/**
	 * Retourne un dossier medical de la base de données a partir d'un nom et un prenom
	 * 
	 * @param record
	 * @return Medicalrecord de la base de données
	 */
	public Medicalrecord returnRecordFromBase(Medicalrecord record) {
		logger.info("Recuperation du dossier spécifié");
		List<Medicalrecord> records = getRecords();
		if (isPresent(record)) {
			for (Medicalrecord recordItem : records) {
				if (recordItem.getFirstName().equals(record.getFirstName())
						&& (recordItem.getLastName().equals(record.getLastName()))) {
					return recordItem;
				}
			}

		}
		return null;
	}

	/**
	 * Verifie si un dossier medical (dans la base) correspond au nom et prénom spécifié 
	 * @param medicalrecord
	 * @return boolean vrai si un dossier medical de la base a le meme nom et prénom que le dossier spécifié
	 */
	public boolean isPresent(Medicalrecord medicalrecord) {
		logger.info("Recherche du dossier "+medicalrecord.getFirstName()+" "+medicalrecord.getLastName());
		List<Medicalrecord> medicalrecords = getRecords();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(medicalrecord.getFirstName())) {
				if (record.getLastName().equals(medicalrecord.getLastName())) {
					return true;
				}
			}

		}
		logger.error("Dossier introuvable");
		return false;

	}

	/**
	 * Ajoute le dossier medical spécifié à la base
	 * @param medicalrecord
	 */
	public void addMedicalrecord(Medicalrecord medicalrecord) {
		logger.info("Ajout du dossier "+medicalrecord.getFirstName()+" "+medicalrecord.getLastName());
		List<Medicalrecord> medicalrecords = getRecords();
		medicalrecords.add(medicalrecord);

	}

	/**
	 * Supprime le dossier mecial spécifié de la base
	 * @param medicalrecord
	 */
	public void removeMedicalrecord(Medicalrecord medicalrecord) {
		logger.info("Suppresion du dossier "+medicalrecord.getFirstName()+" "+medicalrecord.getLastName());
		List<Medicalrecord> medicalrecords = getRecords();
		Medicalrecord recordToRemove = returnRecordFromBase(medicalrecord);
		medicalrecords.remove(recordToRemove);

	}

	/**
	 * Met à jour le dossier medical spécifié
	 * @param medicalrecord
	 */
	public void updateMedicalrecord(Medicalrecord medicalrecord) {
		logger.info("Mise a jour du dossier "+medicalrecord.getFirstName()+" "+medicalrecord.getLastName());
		Medicalrecord recordToUpdate = returnRecordFromBase(medicalrecord);

		recordToUpdate.setBirthdate(medicalrecord.getBirthdate());
		recordToUpdate.setMedications(medicalrecord.getMedications());
		recordToUpdate.setAllergies(medicalrecord.getAllergies());

	}

	/**
	 * Mise à jour partielle du dossier medical spécifié
	 * @param medicalrecord
	 */
	public void patchMedicalrecord(Medicalrecord medicalrecord) {
		logger.info("Mise a jour partielle du dossier "+medicalrecord.getFirstName()+" "+medicalrecord.getLastName());
		Medicalrecord recordToUpdate = returnRecordFromBase(medicalrecord);

		if (!(medicalrecord.getBirthdate() == null)) {
			logger.info("Mise a jour de la date de naissance");
			recordToUpdate.setBirthdate(medicalrecord.getBirthdate());
		}
		if (!(medicalrecord.getMedications() == null)) {
			logger.info("Mise a jour des traitements");
			recordToUpdate.setMedications(medicalrecord.getMedications());
		}
		if (!(medicalrecord.getAllergies() == null)) {
			logger.info("Mise a jour des allergies");
			recordToUpdate.setAllergies(medicalrecord.getAllergies());

		}

	}

	/**
	 * Retourne l'age de la personne
	 * 
	 * @param person
	 * @return int age en année de la personne
	 */
	public int getAge(Person person) {
		logger.info("Recupération de l'age de "+person.getFirstName()+" "+person.getLastName());
		List<Medicalrecord> medicalrecords = getRecords();
		LocalDate today = LocalDate.now();
		Date birthdate;		
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(person.getFirstName())
					&& record.getLastName().equals(person.getLastName())) {

				logger.info("Dossier medical trouve, calcul de l'age actuel en cours");
				birthdate = record.getBirthdate();
				LocalDate birthdateLocal = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Period agePeriod = Period.between(birthdateLocal, today);
				int age = agePeriod.getYears();
				return age;

			}

		}
		System.out.println("Medical Record of the person not found");
		return -1;

	}

	/**
	 * Recupere les traitements medicaux de la personne spécifiée
	 * @param person
	 * @return Liste de medicaments et posologie du dossier medical de la personne spécifiée
	 */
	public List<String> getMedications(Person person) {
		logger.info("Recuperation des traitements medicaux de "+person.getFirstName()+" "+person.getLastName());
		List<Medicalrecord> medicalrecords = getRecords();
		List<String> result = new ArrayList<String>();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(person.getFirstName())
					&& record.getLastName().equals(person.getLastName())) {
				logger.info("Dossier medical trouve, recherche des traitements en cours");
				result = record.getMedications();
				return result;

			}

		}
		System.out.println("Medical Record of the person not found");
		return result;

	}

	/**
	 * Recupere les allergies de la personne spécifiée
	 * @param person
	 * @return liste des allergies de la personne spécifiée
	 */
	public List<String> getAllergies(Person person) {
		logger.info("Recuperation des allergies de "+person.getFirstName()+" "+person.getLastName());
		List<Medicalrecord> medicalrecords = getRecords();
		List<String> result = new ArrayList<String>();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(person.getFirstName())
					&& record.getLastName().equals(person.getLastName())) {
				logger.info("Dossier medical trouve, recherche des allergies en cours");

				result = record.getAllergies();
				return result;

			}

		}
		System.out.println("Medical Record of the person not found");
		return result;

	}

}
