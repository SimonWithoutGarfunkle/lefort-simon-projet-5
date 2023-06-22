package com.safetynetjson.safetynetjson.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.service.CommunityEmail;
import com.safetynetjson.safetynetjson.service.JsonDataService;

@ExtendWith(MockitoExtension.class)
public class CommunityEmailTest {

	private CommunityEmail communityEmail;
	
	private List<Person> persons;

	@Mock
	private JsonDataService jsonDataService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		communityEmail = new CommunityEmail(jsonDataService);

		persons = new ArrayList<Person>();

		Person personinTestDB = new Person();
		personinTestDB.setFirstName("firstNameTest");
		personinTestDB.setLastName("lastNameTest");
		personinTestDB.setCity("TestCity");
		personinTestDB.setEmail("emailTest");
		persons.add(personinTestDB);
		JsonData jsonData = new JsonData();
		jsonData.setPersons(persons);
		when(jsonDataService.getJsonData()).thenReturn(jsonData);

	}

	@Test
	public void getCommunityEmailTest() {
		//Arrange
		
		//Act
		List<String> result = communityEmail.getCommunityEmail("TestCity");
		
		//Assert
		assertEquals(result.size(), 1);

	}
	
	@Test
	public void getCommunityEmailTestWrongCity() {
		//Arrange
		
		//Act
		List<String> result = communityEmail.getCommunityEmail("WrongCity");
		
		//Assert
		assertEquals(result.size(), 0);

	}

}
