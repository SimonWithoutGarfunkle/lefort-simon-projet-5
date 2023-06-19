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
public class MedicalrecordControllerTest {
	
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
	public void getMedicalrecordsTest() throws Exception {
        mockMvc.perform(get("/medicalrecords"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].firstName", hasItem("Tenley")));
		
	}
	
	@Test
	public void postMedicalrecordTest() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"lastNameTest\"}"; 
		
		mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE).content(medicalrecordTest))
        .andExpect(status().isCreated());
		
		mockMvc.perform(get("/medicalrecords"))
		.andExpect(content().string(containsString("TestingBoy")));
	}
	
	@Test
	public void postMedicalrecordTestAlreadyExists() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}"; 
		
		mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE).content(medicalrecordTest))
		.andExpect(status().isBadRequest())
        .andExpect(content().string("Medicalrecord already exists"));
	}
	
	@Test
	public void removeMedicalrecordTest() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";
		mockMvc.perform(delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(medicalrecordTest))
		.andExpect(status().isAccepted());
		
	}
	
	@Test
	public void removeMedicalrecordTestNotPresent() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"VonTesing\"}";
		mockMvc.perform(delete("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(medicalrecordTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Medicalrecord not found"));
		
	}
	
	@Test
	public void putMedicalrecordTest() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";
		mockMvc.perform(put("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(medicalrecordTest))
		.andExpect(status().isAccepted())
		.andExpect(content().string("FirstName and LastName cant be modified. Medicalrecord updated successfully"));
		
		
	}
	
	@Test
	public void putMedicalrecordTestNotPresent() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"VonTesing\"}";
		mockMvc.perform(put("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(medicalrecordTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Medicalrecord not found"));
		
		
	}
	
	@Test
	public void patchMedicalrecordTest() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"Warren\",\"lastName\":\"Zemicks\"}";
		mockMvc.perform(patch("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(medicalrecordTest))
		.andExpect(status().isAccepted())
		.andExpect(content().string("FirstName and LastName cant be modified. Medicalrecord updated successfully"));
		
		
	}
	
	@Test
	public void patchMedicalrecordTestNotPresent() throws Exception {
		String medicalrecordTest = "{\"firstName\":\"TestingBoy\",\"lastName\":\"VonTesing\"}";
		mockMvc.perform(patch("/medicalrecord").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(medicalrecordTest))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Medicalrecord not found"));
		
		
	}
	
	
	
	

}
