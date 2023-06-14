package com.safetynetjson.safetynetjson.service;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.JsonData;

/**
 * Ecrit toute la base de données dans un fichier
 * 
 * @author Simon
 *
 */
@Component
public class JsonWriter {
	
	private static Logger logger = LoggerFactory.getLogger(JsonWriter.class);
	
	/**
	 * Sauvegarde la base de données dans un fichier dans src/main/resources
	 * 
	 * @param jsondata
	 * @param fileName
	 */
	public void writeJson( JsonData jsondata, String fileName) {
		logger.info("Sauvegarde de la base de donnees dans le fichier "+fileName);
		final String FILEPATH = "src/main/resources/"+fileName;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			objectMapper.writeValue(new File(FILEPATH), jsondata);
			
		} catch(IOException e) 	{
			e.printStackTrace();
		}
		
		
		
	}

}
