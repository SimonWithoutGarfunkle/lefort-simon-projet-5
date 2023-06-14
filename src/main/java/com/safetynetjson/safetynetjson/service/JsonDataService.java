package com.safetynetjson.safetynetjson.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
    private static Logger logger = LoggerFactory.getLogger(JsonDataService.class);
	
    public JsonDataService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    /**
     * Extrait la base de données au premier appel puis la transmet lors des appels suivants
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

}
