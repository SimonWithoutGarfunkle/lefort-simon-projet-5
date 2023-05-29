package com.safetynetjson.safetynetjson.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

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
