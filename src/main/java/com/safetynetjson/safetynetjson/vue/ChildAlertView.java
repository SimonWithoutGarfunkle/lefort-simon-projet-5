package com.safetynetjson.safetynetjson.vue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.safetynetjson.safetynetjson.model.PersonWithMedicalrecord;

/**
 * Mise en forme des données pour l'alerte ChildAlert
 * @author Simon
 *
 */
@Component
public class ChildAlertView {
	
	private static final Logger logger = LogManager.getLogger(ChildAlertView.class);

	/**
	 * Organise la liste des personnes en 2 sous listes : les enfants (18 ans et moins) puis les adultes
	 * 
	 * @param persons
	 * @return
	 */
	public Map<String, Object> formateChildAlert(List<PersonWithMedicalrecord> persons) {
		logger.info("Mise en forme des donnees pour ChildAlert");

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
