package com.safetynetjson.safetynetjson.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.PersonService;

@SpringBootTest
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Autowired
	private JsonDataService jsonDataService;
	
	private Person personTest;

	@AfterEach
	public void tearDown() {
	    personService.removePerson(personTest);
	}
	
	@BeforeEach
	public void setUp() {
		personTest = createTestPerson();
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
		JsonData jsonData = jsonDataService.getJsonData();
		List<Person> persons = jsonData.getPersons();
		int lengthPersons = persons.size();

		// Act
		personService.addPerson(personTest);

		// Assert
		assertEquals(lengthPersons + 1, persons.size());

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
