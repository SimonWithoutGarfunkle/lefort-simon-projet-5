package com.safetynetjson.safetynetjson.serviceTest;

import com.safetynetjson.safetynetjson.model.JsonData;
import com.safetynetjson.safetynetjson.service.JsonReader;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class JsonReaderTest {
	
	private JsonReader jsonReader;

	@Test
    public void testReadJson() throws IOException {
        // Arrange
		jsonReader = new JsonReader();

        // Act
		JsonData result = jsonReader.readJson();


        // Assert
		assertNotNull(result);

    }
	
}
