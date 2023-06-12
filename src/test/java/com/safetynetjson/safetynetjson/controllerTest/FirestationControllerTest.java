package com.safetynetjson.safetynetjson.controllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;





@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	@Test
	public void testGetFirestations() throws Exception {
        mockMvc.perform(get("/firestations"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].address", is("1509 Culver St")));
		
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


}
