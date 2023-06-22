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

import com.safetynetjson.safetynetjson.model.Person;
import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;
import com.safetynetjson.safetynetjson.service.ChildByAddress;
import com.safetynetjson.safetynetjson.service.PersonWithMedicalrecordService;

@ExtendWith(MockitoExtension.class)
public class ChildByAddressTest {
	
	private ChildByAddress childByAddress;
	
	@Mock
	private PersonWithMedicalrecordService personWithAgeService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		childByAddress = new ChildByAddress(personWithAgeService);
		
	}
	
	@Test
	public void listOfChildrenTestOnlyAdults() {
		//Arrange
		Person testeur1 = new Person();
		testeur1.setFirstName("firstName1");
		testeur1.setLastName("lastName1");
		Person testeur2 = new Person();
		testeur2.setFirstName("firstName2");
		testeur2.setLastName("lastName2");
		List<Person> listToTest = new ArrayList<Person>();
		listToTest.add(testeur1);
		listToTest.add(testeur2);
		List<PersonWithMedicalrecord> listWithMedicalrecord = new ArrayList<PersonWithMedicalrecord>();
		listWithMedicalrecord.add(new PersonWithMedicalrecord(testeur1, 33));
		listWithMedicalrecord.add(new PersonWithMedicalrecord(testeur2, 45));
		
		when(personWithAgeService.addAgeToPersons(listToTest)).thenReturn(listWithMedicalrecord);
		
		//Act
		List<PersonWithMedicalrecord> result = childByAddress.listOfChildren(listToTest);
		
		//Assert
		assertEquals(result.size(), 0);
		
		
		
	}
	
	@Test
	public void listOfChildrenTestOnlyChildren() {
		//Arrange
		Person testeur1 = new Person();
		testeur1.setFirstName("firstName1");
		testeur1.setLastName("lastName1");
		Person testeur2 = new Person();
		testeur2.setFirstName("firstName2");
		testeur2.setLastName("lastName2");
		List<Person> listToTest = new ArrayList<Person>();
		listToTest.add(testeur1);
		listToTest.add(testeur2);
		List<PersonWithMedicalrecord> listWithMedicalrecord = new ArrayList<PersonWithMedicalrecord>();
		listWithMedicalrecord.add(new PersonWithMedicalrecord(testeur1, 3));
		listWithMedicalrecord.add(new PersonWithMedicalrecord(testeur2, 5));
		
		when(personWithAgeService.addAgeToPersons(listToTest)).thenReturn(listWithMedicalrecord);
		
		//Act
		List<PersonWithMedicalrecord> result = childByAddress.listOfChildren(listToTest);
		
		//Assert
		assertEquals(result.size(), 2);
		
		
		
	}

}
