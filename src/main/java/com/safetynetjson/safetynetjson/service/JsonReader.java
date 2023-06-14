package com.safetynetjson.safetynetjson.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.JsonData;

/**
 * Indique l'emplacement du Json source et extrait son contenu
 * 
 * @author Simon
 *
 */
@Component
public class JsonReader {

	ObjectMapper objectMapper = new ObjectMapper();

	private static final String JSON_FILE_PATH = "src/main/resources/data.json";

	private static Logger logger = LoggerFactory.getLogger(JsonReader.class);

	JsonData jsonData;

	/**
	 * Lit et extrait les données du fichier source
	 * @return la base de données complète
	 */
	public JsonData readJson() {
		logger.info("Lecture du Fichier source Json");

		try {

			jsonData = objectMapper.readValue(new File(JSON_FILE_PATH), JsonData.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;

	}
}