package com.safetynetjson.safetynetjson.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	private PersonService personService;
	
	private Person personTest;
	
	private List<Person> persons;
	
	@Mock
	private JsonDataService jsonDataService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		personService = new PersonService(jsonDataService);
		personTest = createTestPerson();
		persons = new ArrayList<Person>();		
		JsonData jsonData = new JsonData();
		Person personTest = new Person();
		personTest.setFirstName("personTestFirstName");
		persons.add(personTest);
	    jsonData.setPersons(persons);
	    when(jsonDataService.getJsonData()).thenReturn(jsonData);
	}

	private Person createTestPerson() {
		Person person = new Person();
		person.setFirstName("Testeur");
		person.setLastName("VonTesting");
		return person;
	}

	@Test
	public void addPersonTest() {
		// Arrange
		int lengthPersons = persons.size();

		// Act
		personService.addPerson(personTest);

		// Assert
		assertEquals(lengthPersons + 1, persons.size());
		System.out.println(lengthPersons);

	}
	
	
	@Test
	public void isPresentTest() {
		// Arrange
		boolean testBefore = personService.isPresent(personTest);

		// Act
		
		personService.addPerson(personTest);

		// Assert
		boolean testAfter = personService.isPresent(personTest);
	    assertNotEquals(testBefore, testAfter);

	}
	
		
	@Test
	public void removePersonTest() {

		// Assert
		personService.addPerson(personTest);

		// Act
		personService.removePerson(personTest);

		// Assert
		assertFalse(personService.isPresent(personTest));

	}
	
	

}
