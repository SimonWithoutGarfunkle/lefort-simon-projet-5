package com.safetynetjson.safetynetjson.controllerTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.service.JsonDataService;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	JsonData jsondata;
	
	@Autowired
	private JsonDataService jsonDataService;
		
	@BeforeEach
	public void setupTests() {
		jsondata = jsonDataService.updateJsonData();
		
	}
	
	@Test
	public void getPersonsTest() throws Exception {
        mockMvc.perform(get("/persons"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].address", hasItem("834 Binoc Ave")));
		
	}
	
	@Test
	public void postPersonTest() throws Exception {
		String newPerson = "{\"firstName\":\"TestingBoy\",\"lastName\":\"lastNameTest\"}"; 
		
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON_VALUE).content(newPerson))
        .andExpect(status().isCreated());
		
		mockMvc.perform(get("/persons"))
		.andExpect(content().string(containsString("TestingBoy")));
	}
	
	@Test
	public void postPersonTestAddressAlreadyExists() throws Exception {
		String newPerson = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";

	    mockMvc.perform(post("/person")
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(newPerson))
	            .andExpect(status().isBadRequest())
	            .andExpect(content().string("Person already exists"));
	}
	
	@Test
	public void removePersonTest() throws Exception {
		String personTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(personTest))
		.andExpect(status().isAccepted());
		
	}
	
	@Test
	public void removePersonTestNotPresent() throws Exception {
		String personTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"VonTesing\"}";
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(personTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Person not found"));
		
	}
	
	@Test
	public void putPersonTest() throws Exception {
		String personTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";
		mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(personTest))
		.andExpect(status().isAccepted())
		.andExpect(content().string("FirstName and LastName cant be modified. Person updated successfully"));
		
		
	}
	
	@Test
	public void putPersonTestNotPresent() throws Exception {
		String personTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"VonTesing\"}";
		mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(personTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Person not found"));
		
		
	}
	
	@Test
	public void patchPersonTest() throws Exception {
	    String personTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";
	    mockMvc.perform(patch("/person").contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(personTest))
	            .andExpect(status().isAccepted())
	            .andExpect(content().string("FirstName and LastName cant be modified. Person updated successfully"));
	}

	@Test
	public void patchPersonTestNotPresent() throws Exception {
	    String personTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"VonTesing\"}";
	    mockMvc.perform(patch("/person").contentType(MediaType.APPLICATION_JSON_VALUE)
	            .content(personTest))
	            .andExpect(status().isBadRequest())
	            .andExpect(content().string("Person not found"));
	}



}
