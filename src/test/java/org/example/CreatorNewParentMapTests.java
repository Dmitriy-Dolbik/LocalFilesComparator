package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CreatorNewParentMapTests extends BasicTestsCase {
    Map<String, String> parentMap = new HashMap<>();
    Map<String, String> childMap = new HashMap<>();
    CreatorNewParentMap creatorNewParentMap;

    Map<String, String> expectedUpdatedParentMap = new HashMap<>();

    @BeforeEach
    public void beforeEachTest() {
        creatorNewParentMap = new CreatorNewParentMap();
        fillChildMap();
        fillParentMap();
        fillExpectedMap();
    }

    private void fillParentMap() {
        parentMap.put("key1", "value1");
        parentMap.put("key2", "value2_fromparent");
        parentMap.put("key4", "value4");
    }

    private void fillChildMap() {
        childMap.put("key1", "value1");
        childMap.put("key2", "value2_fromchild");
        childMap.put("key3", "value3");
    }

    private void fillExpectedMap() {
        expectedUpdatedParentMap.put("key1", "value1");

        String combinedValue = creatorNewParentMap.getCombinedValue("value2_fromparent", "value2_fromchild");
        expectedUpdatedParentMap.put("key2", combinedValue);

        expectedUpdatedParentMap.put("key3", "value3");
        expectedUpdatedParentMap.put("key4", "value4");
    }

    @Test
    public void getUpdatedParentMapTest_shouldReturnCorrectMap() {
        //When
        Map<String, String> realUpdatedParentmap = creatorNewParentMap.getUpdatedParentMap(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedUpdatedParentMap, realUpdatedParentmap);
    }
}
