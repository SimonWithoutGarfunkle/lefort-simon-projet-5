package com.safetynetjson.safetynetjson.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.model.Medicalrecord;
import com.safetynetjson.safetynetjson.model.Person;

@Service
public class MedicalrecordService {

	private final JsonDataService jsonDataService;

	public MedicalrecordService(JsonDataService jsonDataService) {
		this.jsonDataService = jsonDataService;
	}

	private List<Medicalrecord> getRecords() {
		JsonData jsonData = jsonDataService.getJsonData();
		return jsonData.getMedicalrecords();

	}

	public Medicalrecord returnRecordFromBase(Medicalrecord record) {
		List<Medicalrecord> records = getRecords();
		if (isPresent(record)) {
			for (Medicalrecord recordItem : records) {
				if (recordItem.getFirstName().equals(record.getFirstName())
						&& (recordItem.getLastName().equals(record.getLastName()))) {
					return recordItem;
				}
			}

		}

		return null;
	}

	public boolean isPresent(Medicalrecord medicalrecord) {
		List<Medicalrecord> medicalrecords = getRecords();
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
		List<Medicalrecord> medicalrecords = getRecords();
		medicalrecords.add(medicalrecord);

	}

	public void removeMedicalrecord(Medicalrecord medicalrecord) {
		List<Medicalrecord> medicalrecords = getRecords();
		Medicalrecord recordToRemove = returnRecordFromBase(medicalrecord);
		medicalrecords.remove(recordToRemove);

	}

	public void updateMedicalrecord(Medicalrecord medicalrecord) {
		Medicalrecord recordToUpdate = returnRecordFromBase(medicalrecord);

		recordToUpdate.setBirthdate(medicalrecord.getBirthdate());
		recordToUpdate.setMedications(medicalrecord.getMedications());
		recordToUpdate.setAllergies(medicalrecord.getAllergies());

	}

	public void patchMedicalrecord(Medicalrecord medicalrecord) {
		Medicalrecord recordToUpdate = returnRecordFromBase(medicalrecord);

		if (!(medicalrecord.getBirthdate() == null)) {
			recordToUpdate.setBirthdate(medicalrecord.getBirthdate());
		}
		if (!(medicalrecord.getMedications() == null)) {
			recordToUpdate.setMedications(medicalrecord.getMedications());
		}
		if (!(medicalrecord.getAllergies() == null)) {
			recordToUpdate.setAllergies(medicalrecord.getAllergies());

		}

	}

	public int getAge(Person person) {
		List<Medicalrecord> medicalrecords = getRecords();
		LocalDate today = LocalDate.now();
		Date birthdate;		
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(person.getFirstName())
					&& record.getLastName().equals(person.getLastName())) {

				birthdate = record.getBirthdate();
				LocalDate birthdateLocal = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Period agePeriod = Period.between(birthdateLocal, today);
				int age = agePeriod.getYears();
				return age;

			}

		}
		System.out.println("Medical Record of the person not found");
		return -1;

	}

	public List<String> getMedications(Person person) {
		List<Medicalrecord> medicalrecords = getRecords();
		List<String> result = new ArrayList<String>();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(person.getFirstName())
					&& record.getLastName().equals(person.getLastName())) {

				result = record.getMedications();
				return result;

			}

		}
		System.out.println("Medical Record of the person not found");
		return result;

	}

	public List<String> getAllergies(Person person) {
		List<Medicalrecord> medicalrecords = getRecords();
		List<String> result = new ArrayList<String>();
		for (Medicalrecord record : medicalrecords) {
			if (record.getFirstName().equals(person.getFirstName())
					&& record.getLastName().equals(person.getLastName())) {

				result = record.getAllergies();
				return result;

			}

		}
		System.out.println("Medical Record of the person not found");
		return result;

	}

}
