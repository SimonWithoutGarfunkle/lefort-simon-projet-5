package com.safetynetjson.safetynetjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.service.JsonReader;
import com.safetynetjson.safetynetjson.service.JsonWriter;

@ComponentScan
@SpringBootApplication
public class SafetynetjsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetjsonApplication.class, args);
	}

}
