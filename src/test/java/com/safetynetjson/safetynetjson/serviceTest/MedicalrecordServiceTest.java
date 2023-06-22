package com.safetynetjson.safetynetjson.serviceTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.service.JsonDataService;
import com.safetynetjson.safetynetjson.service.MedicalrecordService;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordServiceTest {
	
	private MedicalrecordService medicalrecordService;

	private Medicalrecord medicalrecordTest;
	
	private Medicalrecord medicalrecordTestNotInDB;

	private List<Medicalrecord> medicalrecords;

	@Mock
	private JsonDataService jsonDataService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		medicalrecordService = new MedicalrecordService(jsonDataService);
		medicalrecords = new ArrayList<Medicalrecord>();
		medicalrecordTest = new Medicalrecord();
		medicalrecordTest.setFirstName("firstNameTest");
		medicalrecordTest.setLastName("lastNameTest");
		medicalrecords.add(medicalrecordTest);		
		JsonData jsonData = new JsonData();
		jsonData.setMedicalrecords(medicalrecords);
		when(jsonDataService.getJsonData()).thenReturn(jsonData);

	}
	
	private Medicalrecord createTestMedicalrecord() {
		Medicalrecord medicalrecord = new Medicalrecord();
		medicalrecord.setFirstName("Testeur");
		medicalrecord.setLastName("VonTesting");
		return medicalrecord;
		
	}
	
	@Test
	public void addMedicalrecordTest() {
		// Arrange
		int lengthMedicalrecords = medicalrecords.size();
		medicalrecordTestNotInDB = createTestMedicalrecord();

		// Act
		medicalrecordService.addMedicalrecord(medicalrecordTestNotInDB);

		// Assert
		assertEquals(lengthMedicalrecords + 1, medicalrecords.size());
		
	}
	
	
	@Test
	public void patchMedicalrecordTest() {
		//Arrange
		List<String> allergies = new ArrayList<>();
		allergies.add("allergie1");
		List<String> medications = new ArrayList<>();
		medications.add("medication1");
		Medicalrecord medicalrecordToUpdate = new Medicalrecord();
		medicalrecordToUpdate.setFirstName("firstNameTest");
		medicalrecordToUpdate.setLastName("lastNameTest");
		medicalrecordToUpdate.setAllergies(allergies);
		medicalrecordToUpdate.setMedications(medications);
		
		String dateString = "01/01/2020";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localdate = LocalDate.parse(dateString, formatter);
        Date date = java.sql.Date.valueOf(localdate);
        medicalrecordToUpdate.setBirthdate(date);
		
		//Act
		medicalrecordService.patchMedicalrecord(medicalrecordToUpdate);
		
		//Assert
		assertNotNull(medicalrecordTest.getBirthdate());
		assertEquals(medicalrecordTest.getAllergies(), allergies);
		assertEquals(medicalrecordTest.getMedications(), medications);
		
		
	}
	
	@Test
	public void returnRecordFromBaseTest() {
		//Arrange
		Medicalrecord recordTest = new Medicalrecord();
		recordTest.setFirstName("firstNameTest");
		recordTest.setLastName("lastNameTest");
		
		//Act
		Medicalrecord result = medicalrecordService.returnRecordFromBase(recordTest);
		
		//Assert
		assertNotNull(result);
		
	}
	
	@Test
	public void returnRecordFromBaseTestNotPresent() {
		//Arrange
		medicalrecordTestNotInDB = createTestMedicalrecord();
		
		//Act
		Medicalrecord result = medicalrecordService.returnRecordFromBase(medicalrecordTestNotInDB);
		
		//Assert
		assertNull(result);
		
	}
	
	@Test
	public void getAgePersonNotFound() {
		//Arrange
		Person personTest = new Person();
		personTest.setFirstName("Testeur");
		personTest.setLastName("TesteurLastName");
		
		//Act
		int result = medicalrecordService.getAge(personTest);
		
		//Assert
		assertTrue(result == -1);
	}
	
	@Test
	public void getMedicationsNotFound() {
		//Arrange
		Person personTest = new Person();
		personTest.setFirstName("Testeur");
		personTest.setLastName("TesteurLastName");
		
		//Act
		List<String> result = medicalrecordService.getMedications(personTest);
		
		//Assert
		assertTrue(result.size() == 0);
		
	}
	
	@Test
	public void getAllergiesNotFound() {
		//Arrange
		Person personTest = new Person();
		personTest.setFirstName("Testeur");
		personTest.setLastName("TesteurLastName");
		
		//Act
		List<String> result = medicalrecordService.getAllergies(personTest);
		
		//Assert
		assertTrue(result.size() == 0);
		
	}
	
	


}
