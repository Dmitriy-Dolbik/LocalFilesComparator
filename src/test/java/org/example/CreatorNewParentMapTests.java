package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.example.TestConstants.KEY_1;
import static org.example.TestConstants.KEY_2;
import static org.example.TestConstants.KEY_3;
import static org.example.TestConstants.KEY_4;
import static org.example.TestConstants.VALUE_1;
import static org.example.TestConstants.VALUE_2_FROM_CHILD;
import static org.example.TestConstants.VALUE_2_FROM_PARENT;
import static org.example.TestConstants.VALUE_3;
import static org.example.TestConstants.VALUE_4;

public class CreatorNewParentMapTests extends BasicTestsCase {
    Map<String, String> parentMap = new HashMap<>();
    Map<String, String> childMap = new HashMap<>();
    CreatorNewParentMap creatorNewParentMap;
    Map<String, String> inputData = new HashMap<>();

    Map<String, String> expectedUpdatedParentMap = new HashMap<>();

    @BeforeEach
    public void beforeEachTest() {
        creatorNewParentMap = new CreatorNewParentMap();
        fillChildMap();
        fillExpectedMap();
        inputData = getDataToInput();
    }

    private void fillChildMap() {
        childMap.put(KEY_1, VALUE_1);
        childMap.put(KEY_2, VALUE_2_FROM_CHILD);
        childMap.put(KEY_3, VALUE_3);
    }

    private void fillExpectedMap() {
        expectedUpdatedParentMap.put(KEY_1, VALUE_1);

        String combinedValue = creatorNewParentMap.getCombinedValue(VALUE_2_FROM_PARENT.toLowerCase(), VALUE_2_FROM_CHILD.toLowerCase());
        expectedUpdatedParentMap.put(KEY_2, combinedValue);

        expectedUpdatedParentMap.put(KEY_3, VALUE_3);
        expectedUpdatedParentMap.put(KEY_4, VALUE_4);
    }

    @Test
    public void getUpdatedParentMapTest_shouldReturnCorrectMap() {
        //Given
        Map<String, String> dataWithValuesInLowerCase = getDataWithValuesInLowerCase(inputData);
        fillParentMap(dataWithValuesInLowerCase);

        //When
        Map<String, String> realUpdatedParentmap = creatorNewParentMap.getUpdatedParentMap(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedUpdatedParentMap, realUpdatedParentmap);
    }

    @Test
    public void getUpdatedParentMapTest_shouldReturnCorrectMap_2() {
        //Given
        Map<String, String> dataWithValuesInUpperCase = getDataWithValuesInUpperCase(inputData);
        fillParentMap(dataWithValuesInUpperCase);

        //When
        Map<String, String> realUpdatedParentmap = creatorNewParentMap.getUpdatedParentMap(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedUpdatedParentMap, realUpdatedParentmap);
    }

    private void fillParentMap(Map<String, String> dataWithValuesInLowerCase) {
        for (String key : dataWithValuesInLowerCase.keySet()) {
            parentMap.put(key, dataWithValuesInLowerCase.get(key));
        }
    }

    private Map<String, String> getDataWithValuesInLowerCase(Map<String, String> dataToInput) {
        Map<String, String> mapWithValuesInLowerCase = new HashMap<>();
        for (String key : dataToInput.keySet()) {
            mapWithValuesInLowerCase.put(key, dataToInput.get(key).toLowerCase());
        }
        return mapWithValuesInLowerCase;
    }

    private Map<String, String> getDataWithValuesInUpperCase(Map<String, String> dataToInput) {
        Map<String, String> mapWithValuesInLowerCase = new HashMap<>();
        for (String key : dataToInput.keySet()) {
            mapWithValuesInLowerCase.put(key, dataToInput.get(key).toUpperCase());
        }
        return mapWithValuesInLowerCase;
    }

    private Map<String, String> getDataToInput() {
        Map<String, String> inputData = new HashMap<>();
        inputData.put(KEY_1, VALUE_1);
        inputData.put(KEY_2, VALUE_2_FROM_PARENT);
        inputData.put(KEY_4, VALUE_4);
        return inputData;

    }
}
