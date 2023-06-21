package com.safetynetjson.safetynetjson.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;

/**
 * Genere la base de données en objet Java à partir des données extraites par le reader
 * @author Simon
 *
 */
@Service
public class JsonDataService {
	
	private JsonData jsonData;
	
	private JsonReader jsonReader;
	
	private static final Logger logger = LogManager.getLogger(JsonDataService.class);
	
    public JsonDataService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    /**
     * Extrait la base de données du fichier json source au premier appel puis la transmet lors des appels suivants
     * 
     * @return la base de données complète
     */
    public JsonData getJsonData() {
    	if (jsonData==null) {
    		logger.info("Instanciation de la base de donnees");
    		jsonData = jsonReader.readJson();
    	}
    	return jsonData;
    }

    public void setJsonData(JsonData jsonData) {
        this.jsonData = jsonData;
    }
    
    /**
     * Réinitialise la base de données pour assurer l'indépendance des tests
     * 
     * @return la base de données 
     */
    public JsonData updateJsonData() {
        JsonReader jsonReader = new JsonReader();
        return jsonData = jsonReader.readJson();
    }
    

}
