package com.safetynetjson.safetynetjson.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Person;

public class JsonReader {

	ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String JSON_FILE_PATH = "src/main/resources/data.json";
	
	JsonData jsonData;
	
	public JsonData readJson() {

	try
	{

		jsonData = objectMapper.readValue(new File(JSON_FILE_PATH), JsonData.class);
		Person[] persons = jsonData.getPersons();
		Firestation[] firestations = jsonData.getFirestations();
		Medicalrecord[] medicalrecords = jsonData.getMedicalrecords();

		
		for (Firestation firestation : firestations) {
            System.out.println(firestation.getStation());
            System.out.println(firestation.getAddress());
            System.out.println("-------------");
        }
		
		for (Person person : persons) {
            System.out.println(person.getFirstName());
            System.out.println(person.getLastName());
            System.out.println(person.getAddress());
            System.out.println(person.getMail());
            System.out.println("-------------");
        }
		
		for (Medicalrecord medicalrecord : medicalrecords) {
            System.out.println(medicalrecord.getFirstName());
            System.out.println(medicalrecord.getLastName());
            System.out.println(medicalrecord.getBirthdate());
            System.out.println(Arrays.toString(medicalrecord.getMedications()));
            System.out.println(Arrays.toString(medicalrecord.getAllergies()));
            System.out.println("-------------");
        }
		

        

	} catch(IOException e) 	{
		e.printStackTrace();
	}
	return jsonData;

}
}