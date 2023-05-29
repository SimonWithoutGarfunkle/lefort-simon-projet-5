package com.safetynetjson.safetynetjson.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Person;

public class JsonReader {

	ObjectMapper objectMapper = new ObjectMapper();
	
	private static final String JSON_FILE_PATH = "src/main/resources/data.json";
	
	public void readJson() {

	try
	{

		JsonData jsonData = objectMapper.readValue(new File(JSON_FILE_PATH), JsonData.class);
		Person[] persons = jsonData.getPersons();
		Firestation[] firestations = jsonData.getFirestations();
		Medicalrecord[] medicalrecords = jsonData.getMedicalrecords();

		
		for (Firestation firestation : firestations) {
            System.out.println(firestation.getStation());
            System.out.println(firestation.getAddress());
            System.out.println("-------------");
        }
		
		for (Person person : persons) {
            System.out.println(person.getFirstname());
            System.out.println(person.getLastname());
            System.out.println(person.getAddress());
            System.out.println(person.getMail());
            System.out.println("-------------");
        }
		
		for (Medicalrecord medicalrecord : medicalrecords) {
            System.out.println(medicalrecord.getFirstname());
            System.out.println(medicalrecord.getLastname());
            System.out.println(medicalrecord.getBirthdate());
            System.out.println(medicalrecord.getMedications());
            System.out.println(medicalrecord.getAllergies());
            System.out.println("-------------");
        }
        

	} catch(IOException e) 	{
		e.printStackTrace();
	}

}
}