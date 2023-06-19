package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.model.JsonData;

/**
 * Service dédié au CRUD et autres requetes basiques pour la table FireStation (caserne de pompiers)
 * @author Simon
 *
 */
@Service
public class FirestationService {

	private final JsonDataService jsonDataService;
	
	private static final Logger logger = LogManager.getLogger(FirestationService.class);

	public FirestationService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	/**
	 * Retourne la liste de toutes les casernes enregistrées
	 * 
	 * @return liste de toutes les casernes
	 */
	private List<Firestation> getFirestations() {
		logger.info("Recuperation de la liste de toutes les casernes enregistrees ");
		JsonData jsonData = jsonDataService.getJsonData();
		return jsonData.getFirestations();

	}

	/**
	 * Vérifie si la caserne donnée est déja enregistrée dans le systeme
	 * 
	 * @param firestation caserne à tester
	 * @return boolean vrai s'il y a deja une caserne avec le meme numéro et la meme adresse connue
	 */
	public boolean isPresent(Firestation firestation) {
		logger.info("Test de la presence de la caserne "+ firestation.getAddress());
		JsonData jsonData = jsonDataService.getJsonData();
		List<Firestation> firestations = jsonData.getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				if (caserne.getStation().equals(firestation.getStation())) {
					logger.info("Caserne deja presente");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Verifie s'il y a une caserne qui couvre l'adresse spécifiée
	 * Contrairement à isPresent, ne vérifie que l'adresse, pas le numéro de caserne
	 * 
	 * @param firestation
	 * @return boolean vrai si l'adresse est présente dans la table Firestation
	 */
	public boolean addressPresent(Firestation firestation) {
		logger.info("Test de la presence de l'adresse de la caserne "+ firestation.getAddress());
		List<Firestation> firestations = getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				logger.info("Adresse deja presente");
				return true;
			}
		}

		return false;
	}

	/**
	 * Vérifie que la caserne spécifiée est présente en base et la renvoie
	 * 
	 * @param firestation
	 * @return la caserne de la base qui correspond a l'adresse et au numéro spécifié
	 */
	public Firestation returnFirestationFromBase(Firestation firestation) {
		logger.info("Recuperation de la caserne demandee");
		List<Firestation> firestations = getFirestations();
		if (isPresent(firestation)) {
			for (Firestation caserne : firestations) {
				if (caserne.getAddress().equals(firestation.getAddress())
						&& caserne.getStation().equals(firestation.getStation())) {
					return caserne;
				}
			}

		}
		logger.error("Caserne introuvable");
		return null;

	}

	/**
	 * Ajout d'une caserne à la base de données
	 * 
	 * @param firestation
	 */
	public void addFirestation(Firestation firestation) {
		logger.info("Ajout a la base de la caserne "+firestation.getAddress());
		List<Firestation> firestations = getFirestations();
		firestations.add(firestation);

	}

	/**
	 * Mise à jour du numéro de station de la caserne
	 * @param firestation
	 */
	public void updateFirestation(Firestation firestation) {
		logger.info("Mise a jour du numero de station de la caserne");
		List<Firestation> firestations = getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				caserne.setStation(firestation.getStation());

			}

		}

	}

	/**
	 * Supprime la caserne si elle est présente
	 * Si seule l'adresse est connue, supprime le mapping avec le n° de caserne pour l'adresse indiquée
	 * @param firestation
	 * @return String précisant l'action effectuée (failed, mapping removed ou firestation removed)
	 */
	public String removeFirestation(Firestation firestation) {
		logger.info("Suppresion de la caserne "+firestation.getAddress());
		List<Firestation> firestations = getFirestations();
		Firestation stationToRemove = returnFirestationFromBase(firestation);
		String result = "Request failed";

		if (firestation.getStation() == null) {
			for (Firestation caserne : firestations) {
				if (caserne.getAddress().equals(firestation.getAddress())) {
					caserne.setStation(null);
					logger.info("Suppresion du numero de caserne a l'adresse indiquee");
					result = "Station mapping removed from this address";

				}
			}

		} else {
			firestations.remove(stationToRemove);
			logger.info("Adresse et numero de caserne supprimes");
			result = "Firestation successfully removed";

		}
		logger.error("Adresse non reconnue");
		return result;
	}

	/**
	 * Retourne les adresses attribuées au numéro de caserne spécifié
	 * 
	 * @param station
	 * @return liste d'adresses
	 */
	public List<String> addressCoveredByStation(Long station) {
		logger.info("Recuperation des adresses liees a la caserne n°"+station);
		List<Firestation> firestations = getFirestations();
		List<String> result = new ArrayList<>();
		for (Firestation firestation : firestations) {
			if (firestation.getStation() == station) {
				result.add(firestation.getAddress());

			}
		}
		return result;

	}

	/**
	 * Appelle la méthode addressCoveredByStation à partir d'une caserne plutot qu'un simple numéro
	 * @param station
	 * @return liste d'adresses
	 */
	public List<String> addressCoveredByStation(Firestation station) {
		logger.info("Recuperation des adresses liees a la caserne");
		return addressCoveredByStation(station.getStation());

	}

	/**
	 * Retourne la caserne attribuée à l'adresse indiquée
	 * @param address
	 * @return caserne attribuée à l'adresse indiquée
	 */
	public Firestation getFirestationFromAddress(String address) {
		logger.info("Recuperation de la caserne a l'adresse "+address);
		List<Firestation> firestations = getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(address)) {
				return caserne;
			}
		}
		logger.error("Adresse introuvable");
		return null;
	}
	
	/**
	 * Retourne le numéro de caserne attribué à l'adresse indiquée
	 * @param address
	 * @return numéro de caserne
	 */
	public Long getStationFromAddress(String address) {
		logger.info("Recuperation du numero de caserne pour l'adresse "+address);
		List<Firestation> firestations = getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(address)) {
				return caserne.getStation();
			}
		}
		logger.error("Adresse introuvable");
		return null;
		
	}

}
