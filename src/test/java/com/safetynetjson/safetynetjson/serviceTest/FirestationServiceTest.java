package com.safetynetjson.safetynetjson.serviceTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.service.FirestationService;
import com.safetynetjson.safetynetjson.service.JsonDataService;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

	private FirestationService firestationService;

	private Firestation firestationTest;

	private List<Firestation> firestations;

	@Mock
	private JsonDataService jsonDataService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		firestationService = new FirestationService(jsonDataService);
		firestations = new ArrayList<Firestation>();
		firestationTest = new Firestation();
		firestationTest.setAddress("addressTest");
		firestationTest.setStation(99L);
		firestations.add(firestationTest);
		JsonData jsonData = new JsonData();
		jsonData.setFirestations(firestations);
		when(jsonDataService.getJsonData()).thenReturn(jsonData);

	}

	@Test
	public void getFirestationFromAddressTest() {
		// Arrange
		Firestation firestationToTest = new Firestation();

		// Act
		firestationToTest = firestationService.getFirestationFromAddress("addressTest");

		// Assert
		assertNotNull(firestationToTest);
	}

	@Test
	public void getFirestationFromAddressTestUnknownAddress() {
		// Arrange
		Firestation firestationToTest = new Firestation();

		// Act
		firestationToTest = firestationService.getFirestationFromAddress("azerty");

		// Assert
		assertNull(firestationToTest);
	}

	@Test
	public void removeFirestationTest() {
		// Arrange
		Firestation firestationToTest = new Firestation();
		firestationToTest.setAddress("addressTest");
		firestationToTest.setStation(99L);

		// Act
		String result = firestationService.removeFirestation(firestationToTest);

		// Assert
		assertEquals(result, "Firestation successfully removed");
		assertTrue(firestations.size() == 0);
	}

	@Test
	public void removeFirestationTestNumberNull() {
		// Arrange
		Firestation firestationToTest = new Firestation();
		firestationToTest.setAddress("addressTest");

		// Act
		String result = firestationService.removeFirestation(firestationToTest);

		// Assert
		assertEquals(result, "Station mapping removed from this address");
		assertTrue(firestations.size() == 1);
	}

	@Test
	public void addressCoveredByStationTest() {
		// Arrange
		Firestation firestationToTest = new Firestation();
		firestationToTest.setAddress("fakeAddress");
		firestationToTest.setStation(99L);
		List<String> addressCovered = new ArrayList<String>();

		// Act
		addressCovered = firestationService.addressCoveredByStation(firestationToTest);

		// Assert
		assertTrue(addressCovered.size() == 1);
		assertTrue(addressCovered.contains("addressTest"));
	}

	@Test
	public void getStationFromAddressTest() {
		// Arrange
		Long stationtest;

		// Act
		stationtest = firestationService.getStationFromAddress("addressTest");

		// Assert
		assertTrue(stationtest == 99);

	}

	@Test
	public void getStationFromAddressTestUnknownAddress() {
		// Arrange
		Long stationtest;

		// Act
		stationtest = firestationService.getStationFromAddress("aaaaaaaaaaaa");

		// Assert
		assertNull(stationtest);

	}

	@Test
	public void returnFirestationFromBaseTest() {
		// Arrange
		Firestation firestationToTest = new Firestation();
		firestationToTest.setAddress("addressTest");
		firestationToTest.setStation(99L);
		Firestation result = new Firestation();

		// Act
		result = firestationService.returnFirestationFromBase(firestationToTest);

		// Assert
		assertTrue(firestationService.isPresent(result));

	}

	@Test
	public void returnFirestationFromBaseTestIllogicalData() {
		// Arrange
		FirestationService firestationService = Mockito.spy(new FirestationService(jsonDataService));
		Firestation firestationToTest = new Firestation();
		firestationToTest.setAddress("addressToTest");
		firestationToTest.setStation(1L);
		Mockito.doReturn(true).when(firestationService).isPresent(firestationToTest);

		// Act
		Firestation result = firestationService.returnFirestationFromBase(firestationToTest);

		// Assert
		assertNull(result);

	}

}
