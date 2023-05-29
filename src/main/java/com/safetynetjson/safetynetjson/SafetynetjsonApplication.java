package com.safetynetjson.safetynetjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynetjson.safetynetjson.util.JsonData;
import com.safetynetjson.safetynetjson.util.JsonReader;
import com.safetynetjson.safetynetjson.util.JsonWriter;

@SpringBootApplication
public class SafetynetjsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetjsonApplication.class, args);
		
		JsonReader jsonReader = new JsonReader();
        JsonData jsonData = jsonReader.readJson();
        
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.writeJson(jsonData, "test1.json");
	}

}
