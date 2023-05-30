package com.safetynetjson.safetynetjson.service;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;

@Service
public class JsonDataService {
	
	private JsonData jsonData;
	
	private JsonReader jsonReader;
	
    public JsonDataService(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public JsonData getJsonData() {
    	if (jsonData==null) {
    		jsonData = jsonReader.readJson();
    	}
        return jsonData;
    }

    public void setJsonData(JsonData jsonData) {
        this.jsonData = jsonData;
    }

}
