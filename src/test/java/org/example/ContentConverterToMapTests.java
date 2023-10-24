package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentConverterToMapTests extends BasicTestsCase {
    private ContentConverterToMap contentConverterToMap;
    private List<String> fileContent = new ArrayList<>();
    private Map<String, String> expectedContentMap;

    @BeforeEach
    public void beforeEachTest() {
        contentConverterToMap = new ContentConverterToMap();
        fillExpectedFileMap();
    }

    private void fillExpectedFileMap() {
        expectedContentMap = new HashMap<>();
        expectedContentMap.put("key1", "value1");
        expectedContentMap.put("key3", "value3");
    }

    @Test
    public void convertFileContentToMapTest_valuesAreInLowerCases_shouldReturnCorrectMap() {
        //Given
        fileContent.add("key1=value1");
        fileContent.add("key3=value3");

        //When
        Map<String, String> realContentMap = ContentConverterToMap.convertFileContentToMap(fileContent);

        //Then
        assertEqualsMaps(expectedContentMap, realContentMap);
    }

    @Test
    public void convertFileContentToMapTest_thereAreSpaceSigns_shouldReturnCorrectMap() {
        //Given
        fileContent.add("key1=value1");
        fileContent.add("key3 = value3");

        //When
        Map<String, String> realContentMap = ContentConverterToMap.convertFileContentToMap(fileContent);

        //Then
        assertEqualsMaps(expectedContentMap, realContentMap);
    }

    @Test
    public void convertFileContentToMapTest_valuesInUpperCases_shouldReturnCorrectMap() {
        //Given
        fileContent.add("key1=VALUE1");
        fileContent.add("key3=VALUE3");

        //When
        Map<String, String> realContentMap = ContentConverterToMap.convertFileContentToMap(fileContent);

        //Then
        assertEqualsMaps(expectedContentMap, realContentMap);
    }
}
