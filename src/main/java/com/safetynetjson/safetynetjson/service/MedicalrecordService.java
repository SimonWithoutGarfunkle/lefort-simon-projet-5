package com.safetynetjson.safetynetjson.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Medicalrecord;

@Service
public class MedicalrecordService {
	
	private final JsonDataService jsonDataService;

	public MedicalrecordService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}
	
	public boolean isPresent(Medicalrecord medicalrecord) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(medicalrecord.getFirstName())) {
				if (record.getLastName().equals(medicalrecord.getLastName())) {
					return true;
				}
			}

		}
		return false;

	}

	public void addMedicalrecord(Medicalrecord medicalrecord) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();

		medicalrecords.add(medicalrecord);

	}

	public void removeMedicalrecord(Medicalrecord medicalrecord) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals((medicalrecord).getFirstName())) {
				if (record.getLastName().equals(medicalrecord.getLastName())) {
					medicalrecords.remove(record);
					break;
				}

			}
		}

	}
	
	public void updateMedicalrecord(Medicalrecord medicalrecord) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals((medicalrecord).getFirstName())) {
				if (record.getLastName().equals(medicalrecord.getLastName())) {
					record.setBirthdate(medicalrecord.getBirthdate());
					record.setMedications(medicalrecord.getMedications());
					record.setAllergies(medicalrecord.getAllergies());
				}

			}
		}

	}

	public void patchMedicalrecord(Medicalrecord medicalrecord) {
		JsonData jsonData = jsonDataService.getJsonData();
		List<Medicalrecord> medicalrecords = jsonData.getMedicalrecords();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals((medicalrecord).getFirstName())) {
				if (record.getLastName().equals(medicalrecord.getLastName())) {
					if (!(medicalrecord.getBirthdate() == null)) {
						record.setBirthdate(medicalrecord.getBirthdate());
					}
					if (!(medicalrecord.getMedications() == null)) {
						record.setMedications(medicalrecord.getMedications());
					}
					if (!(medicalrecord.getAllergies() == null)) {
						record.setAllergies(medicalrecord.getAllergies());

					}


				}

			}
		}

	}


}
