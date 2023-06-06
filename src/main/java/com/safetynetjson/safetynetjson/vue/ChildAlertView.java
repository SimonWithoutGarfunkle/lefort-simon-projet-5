package com.safetynetjson.safetynetjson.vue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;


@Component
public class ChildAlertView {

	public Map<String, Object> formateChildAlert(List<PersonWithMedicalrecord> persons) {

		Map<String, Object> response = new HashMap<>();

		List<Map<String, Object>> childrenList = new ArrayList<>();
		List<Map<String, Object>> adultsList = new ArrayList<>();

		for (PersonWithMedicalrecord person : persons) {
			if (person.getAge() <= 18) {
				Map<String, Object> personData = new HashMap<>();
				personData.put("firstName", person.getFirstName());
				personData.put("lastName", person.getLastName());
				personData.put("age", person.getAge());
				childrenList.add(personData);
			} else {
				Map<String, Object> personData = new HashMap<>();
				personData.put("firstName", person.getFirstName());
				personData.put("lastName", person.getLastName());
				personData.put("age", person.getAge());
				adultsList.add(personData);
			}
		}
		response.put("adultsAtAddress", adultsList);
		response.put("childrenAtAddress", childrenList);


		return response;
	}

}
