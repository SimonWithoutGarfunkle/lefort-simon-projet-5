package com.safetynetjson.safetynetjson.vue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class FloodStationView {
	public Map<String, Object> formatFloodStation(List<String> coveredAddresses, List<Map<String, Object>> personsData) {
	    Map<String, Object> response = new HashMap<>();
	    response.put("coveredAddresses", coveredAddresses);
	    response.put("addressesAndPersons", personsData);
	    return response;
	}

}
