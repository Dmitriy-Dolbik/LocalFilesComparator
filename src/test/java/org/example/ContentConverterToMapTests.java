package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Constants.EQUAL_SIGN;
import static org.example.Constants.SPASE_SIGN;
import static org.example.TestConstants.KEY_1;
import static org.example.TestConstants.KEY_3;
import static org.example.TestConstants.VALUE_1;
import static org.example.TestConstants.VALUE_3;

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
        expectedContentMap.put(KEY_1, VALUE_1);
        expectedContentMap.put(KEY_3, VALUE_3);
    }

    @Test
    public void convertFileContentToMapTest_shouldReturnCorrectMap() {
        //Given
        fileContent.add(KEY_1 + EQUAL_SIGN + VALUE_1);
        fileContent.add(KEY_3 + SPASE_SIGN + EQUAL_SIGN + SPASE_SIGN + VALUE_3);

        //When
        Map<String, String> realContentMap = ContentConverterToMap.convertFileContentToMap(fileContent);

        //Then
        assertEqualsMaps(expectedContentMap, realContentMap);
    }

    @Test
    public void convertFileContentToMapTest_shouldReturnCorrectMap_2() {
        //Given
        fileContent.add(KEY_1 + EQUAL_SIGN + VALUE_1);
        fileContent.add(KEY_3 + SPASE_SIGN + EQUAL_SIGN + SPASE_SIGN + VALUE_3.toUpperCase());

        //When
        Map<String, String> realContentMap = ContentConverterToMap.convertFileContentToMap(fileContent);

        //Then
        assertEqualsMaps(expectedContentMap, realContentMap);
    }
}
