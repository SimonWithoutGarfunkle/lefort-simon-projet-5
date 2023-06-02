package com.safetynetjson.safetynetjson.vue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.safetynetjson.safetynetjson.model.PersonWithAge;


@Component
@JsonPropertyOrder({ "childrenAtAddress", "adultsAtAddress" })
public class ChildAlertView {

	public Map<String, Object> formateChildAlert(List<PersonWithAge> persons) {

		Map<String, Object> response = new HashMap<>();

		List<Map<String, Object>> childrenList = new ArrayList<>();
		List<Map<String, Object>> adultsList = new ArrayList<>();

		for (PersonWithAge person : persons) {
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
