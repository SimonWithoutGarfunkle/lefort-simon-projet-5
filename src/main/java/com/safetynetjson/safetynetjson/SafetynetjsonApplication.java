package com.safetynetjson.safetynetjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:log4j2.yml")
@ComponentScan
@SpringBootApplication
public class SafetynetjsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetjsonApplication.class, args);
	}

}
