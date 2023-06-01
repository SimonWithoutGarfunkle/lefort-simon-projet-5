package com.safetynetjson.safetynetjson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.Firestation;
import com.safetynetjson.safetynetjson.model.JsonData;

@Service
public class FirestationService {

	private final JsonDataService jsonDataService;

	public FirestationService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	public boolean isPresent(Firestation firestation) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Firestation> firestations = jsonData.getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				if (caserne.getStation().equals(firestation.getStation())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean addressPresent(Firestation firestation) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Firestation> firestations = jsonData.getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				return true;
			}
		}

		return false;
	}

	public void addFirestation(Firestation firestation) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Firestation> firestations = jsonData.getFirestations();

		firestations.add(firestation);

	}


	
	public void updateFirestation(Firestation firestation) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Firestation> firestations = jsonData.getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				caserne.setStation(firestation.getStation());

			}

		}

	}

	public String removeFirestation(Firestation firestation) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Firestation> firestations = jsonData.getFirestations();
		String result = "Request failed";

		if (firestation.getStation() == null) {
			for (Firestation caserne : firestations) {
				if (caserne.getAddress().equals(firestation.getAddress())) {
					caserne.setStation(null);
					result = "Station mapping removed from this address";
					

				}
			}

		} else {
			for (Firestation caserne : firestations) {
				if (caserne.getAddress().equals(firestation.getAddress())) {
					if (caserne.getStation().equals(firestation.getStation())) {
						firestations.remove(caserne);
						result = "Firestation successfully removed";
						break;			
					}
				}
			}

		}
		return result;

	}
	
	public List<String> addressCoveredByStation (Long station) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<String> result = new ArrayList<>();
		List<Firestation> firestations = jsonData.getFirestations();
		for (Firestation firestation : firestations) {
			if (firestation.getStation()==station) {
				result.add(firestation.getAddress());
				
			}
		}
		return result;
		
	}
	
	public List<String> addressCoveredByStation (Firestation station) {
		return addressCoveredByStation(station.getStation());
		
	}

}
