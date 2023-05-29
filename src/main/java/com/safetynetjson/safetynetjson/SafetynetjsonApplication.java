package com.safetynetjson.safetynetjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynetjson.safetynetjson.util.JsonReader;

@SpringBootApplication
public class SafetynetjsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetjsonApplication.class, args);
		
		JsonReader jsonReader = new JsonReader();
        jsonReader.readJson();
	}

}
