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

	private List<Firestation> getFirestations() {
		JsonData jsonData = jsonDataService.getJsonData();
		return jsonData.getFirestations();

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
		List<Firestation> firestations = getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				return true;
			}
		}

		return false;
	}

	public Firestation returnFirestationFromBase(Firestation firestation) {
		List<Firestation> firestations = getFirestations();
		if (isPresent(firestation)) {
			for (Firestation caserne : firestations) {
				if (caserne.getAddress().equals(firestation.getAddress())
						&& caserne.getStation().equals(firestation.getStation())) {
					return caserne;
				}
			}

		}
		return null;

	}

	public void addFirestation(Firestation firestation) {
		List<Firestation> firestations = getFirestations();
		firestations.add(firestation);

	}

	public void updateFirestation(Firestation firestation) {
		List<Firestation> firestations = getFirestations();
		for (Firestation caserne : firestations) {
			if (caserne.getAddress().equals(firestation.getAddress())) {
				caserne.setStation(firestation.getStation());

			}

		}

	}

	public String removeFirestation(Firestation firestation) {
		List<Firestation> firestations = getFirestations();
		Firestation stationToRemove = returnFirestationFromBase(firestation);
		String result = "Request failed";

		if (firestation.getStation() == null) {
			for (Firestation caserne : firestations) {
				if (caserne.getAddress().equals(firestation.getAddress())) {
					caserne.setStation(null);
					result = "Station mapping removed from this address";

				}
			}

		} else {
			firestations.remove(stationToRemove);
			result = "Firestation successfully removed";

		}
		return result;
	}

	public List<String> addressCoveredByStation(Long station) {
		List<Firestation> firestations = getFirestations();
		List<String> result = new ArrayList<>();
		for (Firestation firestation : firestations) {
			if (firestation.getStation() == station) {
				result.add(firestation.getAddress());

			}
		}
		return result;

	}

	public List<String> addressCoveredByStation(Firestation station) {
		return addressCoveredByStation(station.getStation());

	}

}
