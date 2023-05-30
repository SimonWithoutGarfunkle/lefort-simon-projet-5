package com.safetynetjson.safetynetjson.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.JsonData;

@Component
public class JsonReader {

	ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String JSON_FILE_PATH = "src/main/resources/data.json";
	
	JsonData jsonData;
	
	public JsonData readJson() {

	try
	{

		jsonData = objectMapper.readValue(new File(JSON_FILE_PATH), JsonData.class);   

	} catch(IOException e) 	{
		e.printStackTrace();
	}
	return jsonData;

}
}