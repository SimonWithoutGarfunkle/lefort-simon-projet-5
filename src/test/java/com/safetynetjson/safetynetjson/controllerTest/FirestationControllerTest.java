package com.safetynetjson.safetynetjson.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynetjson.safetynetjson.service.JsonDataService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;





@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Autowired
	private JsonDataService jsonDataService;
		
	@BeforeEach
	public void setupTests() {
		jsonDataService.updateJsonData();
		
	}
	
	
	@Test
	public void getFirestationsTest() throws Exception {
        mockMvc.perform(get("/firestations"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].address", hasItem("834 Binoc Ave")));
		
	}
	
	@Test
	public void postFirestationTest() throws Exception {
		String newFirestationJson = "{\"address\":\"fake address for tests\",\"station\":\"0\"}"; 
		
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE).content(newFirestationJson))
        .andExpect(status().isCreated());
		
		mockMvc.perform(get("/firestations"))
		.andExpect(content().string(containsString("fake address")));
	}
	
	@Test
	public void postFirestationTestAddressAlreadyExists() throws Exception {
	    String newFirestationJson = "{\"address\":\"1509 Culver St\",\"station\":\"0\"}";

	    mockMvc.perform(post("/firestation")
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(newFirestationJson))
	            .andExpect(status().isBadRequest())
	            .andExpect(content().string("Address already registered"));
	}
	
	@Test
	public void removeFirestationTest() throws Exception {
		String firestationTest = "{\"address\":\"1509 Culver St\",\"station\":\"3\"}";
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(firestationTest))
		.andExpect(status().isAccepted());
		
	}
	
	@Test
	public void removeFirestationTestNotPresent() throws Exception {
		String firestationTest = "{\"address\":\"adressTest\",\"station\":\"99\"}";
		mockMvc.perform(delete("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(firestationTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Firestation not found"));
		
	}
	
	@Test
	public void putFirestationTest() throws Exception {
		String firestationTest = "{\"address\":\"644 Gershwin Cir\",\"station\":\"99\"}";
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(firestationTest))
		.andExpect(status().isAccepted())
		.andExpect(content().string("Firestation station updated successfully"));
		
		
	}
	
	@Test
	public void putFirestationTestWrongAddress() throws Exception {
		String firestationTest = "{\"address\":\"999 testing street\",\"station\":\"99\"}";
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(firestationTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Firestation not found"));
		
		
	}
	
	
	@Test
	public void getPersonsByStationTest() throws Exception {
	    Long stationNumber = 1L;

	    mockMvc.perform(get("/firestation")
	            .param("stationNumber", stationNumber.toString())
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.persons[*].firstName").isNotEmpty())
	            .andExpect(jsonPath("$.persons[*].lastName").isNotEmpty())
	            .andExpect(jsonPath("$.personCount").isNumber())
	            .andExpect(jsonPath("$.childrenCount").isNumber());
	}


}
