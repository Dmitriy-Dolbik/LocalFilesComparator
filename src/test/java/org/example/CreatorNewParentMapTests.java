package org.example;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CreatorNewParentMapTests extends BasicTestsCase {

    CreatorNewParentMap creatorNewParentMap = new CreatorNewParentMap();

    @Test
    public void getUpdatedParentMapTest_shouldReturnCorrectMap() {
        //Given
        Map<String, String> parentMap = new HashMap<>();
        parentMap.put("key1", "value1");
        parentMap.put("key2", "value2_fromparent");
        parentMap.put("key4", "value4");

        Map<String, String> childMap = new HashMap<>();
        childMap.put("key1", "value1");
        childMap.put("key2", "value2_fromchild");
        childMap.put("key3", "value3");

        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("key1", "value1");
        expectedMap.put("key2", creatorNewParentMap.getCombinedValue("value2_fromparent", "value2_fromchild"));
        expectedMap.put("key3", "value3");
        expectedMap.put("key4", "value4");

        //When
        Map<String, String> realMap = creatorNewParentMap.getUpdatedParentMap(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedMap, realMap);
    }
}
