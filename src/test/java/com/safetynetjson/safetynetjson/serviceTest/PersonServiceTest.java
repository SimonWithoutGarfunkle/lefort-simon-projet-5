package com.safetynetjson.safetynetjson.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
		persons = new ArrayList<Person>();
		
		Person personinTestDB = new Person();
		personinTestDB.setFirstName("firstNameTest");
		personinTestDB.setLastName("lastNameTest");
		persons.add(personinTestDB);
				
		personTest = createTestPerson();
		
		JsonData jsonData = new JsonData();
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
	public void addPersonTest() {
		// Arrange
		int lengthPersons = persons.size();

		// Act
		personService.addPerson(personTest);

		// Assert
		assertEquals(lengthPersons + 1, persons.size());

	}



	@Test
	public void removePersonTest() {

		// Arrange
		personService.addPerson(personTest);

		// Act
		personService.removePerson(personTest);

		// Assert
		assertFalse(personService.isPresent(personTest));

	}

	@Test
	public void patchPersonTest() {
	    // Arrange
	    Person personToUpdate = new Person();
	    personToUpdate.setFirstName("John");
	    personToUpdate.setLastName("Doe");
	    personToUpdate.setAddress("123 Street");
	    personToUpdate.setCity("City");
	    personToUpdate.setZip("12345");
	    personToUpdate.setPhone("123456789");
	    personToUpdate.setEmail("john.doe@example.com");

	    Person updatedPerson = new Person();
	    updatedPerson.setFirstName("John");
	    updatedPerson.setLastName("Doe");
	    personService.addPerson(updatedPerson);

	    // Act
	    personService.patchPerson(personToUpdate);

	    // Assert
	    assertEquals("City", updatedPerson.getCity());
	    assertEquals("123 Street", updatedPerson.getAddress());
	    assertEquals("12345", updatedPerson.getZip());
	    assertEquals("123456789", updatedPerson.getPhone());
	    assertEquals("john.doe@example.com", updatedPerson.getEmail());
	}
	
	@Test
	public void patchPersonTestWithNull() {
	    // Arrange
	    Person personToUpdate = new Person();
	    personToUpdate.setFirstName("John");
	    personToUpdate.setLastName("Doe");
	    personToUpdate.setAddress("123 Street");
	    personToUpdate.setCity("City");
	    personToUpdate.setZip("12345");
	    personToUpdate.setPhone("123456789");
	    personToUpdate.setEmail("john.doe@example.com");
	    personService.addPerson(personToUpdate);

	    Person updatedPerson = new Person();
	    updatedPerson.setFirstName("John");
	    updatedPerson.setLastName("Doe");

	    // Act
	    personService.patchPerson(updatedPerson);

	    // Assert
	    assertEquals("City", personToUpdate.getCity());
	    assertEquals("123 Street", personToUpdate.getAddress());
	    
		
	}
	
	@Test
	public void returnPersonFromBaseTest() {
		// Arrange
		Person personNameTest = createTestPerson();
		personService.addPerson(personNameTest);
		Person testIsPresentPerson = new Person();
		testIsPresentPerson.setFirstName("firstNameTest");
		testIsPresentPerson.setLastName("lastNameTest");
		
		// Act
		Person returnPersonTest = personService.returnPersonFromBase(testIsPresentPerson);
		
		// Assert
		assertNotNull(returnPersonTest);
		
	}
	
	@Test
	public void returnPersonFromBaseTestNotPresent() {
		// Arrange
		Person testIsPresentPerson = createTestPerson();
		
		// Act
		Person returnPersonTest = personService.returnPersonFromBase(testIsPresentPerson);
		
		// Assert
		assertNull(returnPersonTest);
		
	}
	
	@Test
	public void returnPersonFromBaseTestOnlyFirstNameFound() {
		// Arrange
		Person testIsPresentPerson = new Person();
		testIsPresentPerson.setFirstName("firstNameTest");
		testIsPresentPerson.setLastName("azerty");
		
		
		// Act
		Person returnPersonTest = personService.returnPersonFromBase(testIsPresentPerson);
		
		// Assert
		assertNull(returnPersonTest);
		
	}


}
