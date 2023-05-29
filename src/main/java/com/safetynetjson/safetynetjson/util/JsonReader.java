package com.safetynetjson.safetynetjson.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.Person;

public class JsonReader {

	ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String JSON_FILE_PATH = "src/main/resources/datapersons.json";
	
	public void readJson() {

	try
	{

		List<Person> persons = objectMapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<Person>>(){});
		
		for (Person person : persons) {
            System.out.println(person.getFirstname());
            System.out.println(person.getLastname());
            System.out.println(person.getAddress());
            System.out.println(person.getMail());
            System.out.println("-------------");
        }

	} catch(IOException e) 	{
		e.printStackTrace();
	}

}
}