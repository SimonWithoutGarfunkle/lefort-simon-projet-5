package com.safetynetjson.safetynetjson.serviceTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.JsonReader;

@ExtendWith(MockitoExtension.class)
public class JsonDataServiceTest {
	
	private JsonData jsonData;
	
	private JsonDataService jsonDataService;
	
	@Mock
	private JsonReader jsonReader;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		jsonDataService = new JsonDataService(jsonReader);
		jsonData = new JsonData();		

	}
	
	@Test
	public void getJsonDataTest() {
		//Arrange
		List<Person> personsTest = new ArrayList<Person>();
		jsonData.setPersons(personsTest);
		when(jsonReader.readJson()).thenReturn(jsonData);
		
		
		//Act
		JsonData result = jsonDataService.getJsonData();
		
		//Assert
		assertNotNull(result);
		
	}
	
	@Test
	public void getJsonDataTestNull() {
		//Arrange
		when(jsonReader.readJson()).thenReturn(null);
		
		//Act
		JsonData result = jsonDataService.getJsonData();
		
		//Assert
		assertNull(result);
		
	}

}
