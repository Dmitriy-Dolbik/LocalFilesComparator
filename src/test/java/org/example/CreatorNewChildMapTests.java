package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CreatorNewChildMapTests extends BasicTestsCase{
    CreatorNewChildMap creatorNewChildMap = new CreatorNewChildMap();

    @Test
    public void createMapTest_shouldReturnCorrectMap() {
        //Given
        Map<String, String> updatedParentMap = new HashMap<>();
        updatedParentMap.put("key1", "value1_updatedParentMap");
        updatedParentMap.put("key2", "value2_updatedParentMap");
        updatedParentMap.put("key4", "value4_updatedParentMap");

        Map<String, String> basicLocalChildMap = new HashMap<>();
        basicLocalChildMap.put("key1", "value1_basicLocalChildMap");
        basicLocalChildMap.put("key2", "value2_basicLocalChildMap");
        basicLocalChildMap.put("key3", "value3_basicLocalChildMap");

        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("key3", creatorNewChildMap.getMissingValue());
        expectedMap.put("key1", "value1_updatedParentMap");
        expectedMap.put("key2", "value2_updatedParentMap");

        //When
        Map<String, String> realMap = creatorNewChildMap.createMap(updatedParentMap, basicLocalChildMap);

        //Then
        assertEqualsMaps(expectedMap, realMap);
    }
}
