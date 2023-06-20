package com.safetynetjson.safetynetjson.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.MedicalrecordService;
import com.safetynetjson.safetynetjson.service.PersonWithMedicalrecordService;

@ExtendWith(MockitoExtension.class)
public class PersonWithMedicalrecordServiceTest {
	
	private PersonWithMedicalrecordService personWithMedicalrecordService;	
	
	@Mock
	private MedicalrecordService  medicalrecordService;
	
	@Mock
	private JsonDataService jsonDataService;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		personWithMedicalrecordService = new PersonWithMedicalrecordService(medicalrecordService);
		
	}
	
	
	@Test
	public void isAChildTest() {
		//Arrange
		Person personToTest = new Person();
		personToTest.setFirstName("Testeur");
		personToTest.setLastName("VonTesting");
		when(medicalrecordService.getAge(personToTest)).thenReturn(5);
		
		//Act
		boolean result = personWithMedicalrecordService.isAChild(personToTest);
		
		//Assert
		assertTrue(result);
	}
	
	@Test
	public void isAChildTestAdult() {
		//Arrange
		Person personToTest = new Person();
		personToTest.setFirstName("Testeur");
		personToTest.setLastName("VonTesting");
		when(medicalrecordService.getAge(personToTest)).thenReturn(40);
		
		//Act
		boolean result = personWithMedicalrecordService.isAChild(personToTest);
		
		//Assert
		assertFalse(result);
	}
	
	@Test
	public void countPeopleTest() {
		//Arrange
		List<PersonWithMedicalrecord> listToTest = new ArrayList<PersonWithMedicalrecord>();
		Person personToTest = new Person();
		personToTest.setFirstName("Testeur");
		personToTest.setLastName("VonTesting");
		PersonWithMedicalrecord personTest = new PersonWithMedicalrecord(personToTest, 50);
		listToTest.add(personTest);
		
		//Act
		int result = personWithMedicalrecordService.countPeople(listToTest);
		
		//Assert
		assertEquals(result, 1);
		
	}

}
