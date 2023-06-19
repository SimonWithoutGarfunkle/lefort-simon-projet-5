package com.safetynetjson.safetynetjson.controllerTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class AlertControllerTest {
	
	
	
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
	public void getChildByAddressTest() throws Exception {
        String addressTest = "947 E. Rose Dr";
		mockMvc.perform(get("/childAlert")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .param("address", addressTest))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsAtAddress[*].lastName", hasItem("Stelzer")));
		
	}
	
	@Test
	public void getPhoneByStationNumberTest() throws Exception {
		mockMvc.perform(get("/phoneAlert")
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .param("station", "3"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.phone[*]", hasItem("841-874-6512")));
		
	}
	
	@Test
	public void getPersonsByStationsTest() throws Exception {
	    mockMvc.perform(get("/flood/stations")
	            .contentType(MediaType.APPLICATION_JSON_VALUE)
	            .param("stationNumber", "1")
	            .param("stationNumber", "2")
	            .param("stationNumber", "3"))
	            .andExpect(status().isOk())
		        .andExpect(jsonPath("$.coveredAddresses[*]", hasItem("644 Gershwin Cir")));
		
	}
	
	@Test
	public void getEmailOfCityTest() throws Exception {
		mockMvc.perform(get("/communityEmail")
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .param("city", "Culver"))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$[*]", hasItem("jpeter@email.com")));
		
	}
	
	@Test
	public void getInfoPersonTest() throws Exception {
		mockMvc.perform(get("/personInfo")
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .param("firstName", "Zach")
		        .param("lastName", "Zemicks"))
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("zarc@email.com")));
		
	}
	
	@Test
	public void getPersonsByAddressTest() throws Exception {
		mockMvc.perform(get("/fire")
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .param("address", "947 E. Rose Dr"))
		        .andExpect(status().isOk())
		        .andExpect(content().string(containsString("Shawna")));
		
	}
	
	

}
