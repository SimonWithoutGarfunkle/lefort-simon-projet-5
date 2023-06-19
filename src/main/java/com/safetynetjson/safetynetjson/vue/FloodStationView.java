package com.safetynetjson.safetynetjson.vue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Mise en forme des données pour l'alerte Flood Station
 * @author Simon
 *
 */
@Component
public class FloodStationView {
	private static final Logger logger = LogManager.getLogger(FloodStationView.class);

	/**
	 * Organise la réponse en une liste contenant d'abord la liste des adresses couvertes puis la liste des personnes
	 * @param coveredAddresses
	 * @param personsData
	 * @return liste contenant les adresses couvertes et les personnes
	 */
	public Map<String, Object> formatFloodStation(List<String> coveredAddresses, List<Map<String, Object>> personsData) {
	    logger.info("Mise en forme du resultat de l'alerte Flood Station");
		Map<String, Object> response = new HashMap<>();
	    response.put("coveredAddresses", coveredAddresses);
	    response.put("addressesAndPersons", personsData);
	    return response;
	}

}
