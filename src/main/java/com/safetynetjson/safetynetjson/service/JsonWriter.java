package com.safetynetjson.safetynetjson.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.JsonData;

@Component
public class JsonWriter {
	
	public void writeJson( JsonData jsondata, String fileName) {
		
		final String FILEPATH = "src/main/resources/"+fileName;
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			objectMapper.writeValue(new File(FILEPATH), jsondata);
			
		} catch(IOException e) 	{
			e.printStackTrace();
		}
		
		
		
	}

}
