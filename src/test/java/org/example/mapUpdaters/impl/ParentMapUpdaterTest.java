package org.example.mapUpdaters.impl;

import org.example.BasicTestsCase;
import org.example.mapUpdaters.MapUpdater;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ParentMapUpdaterTest extends BasicTestsCase {

    MapUpdater parentMapUpdater = new ParentMapUpdater();

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
        expectedMap.put("key2", "value2_fromparent");
        expectedMap.put("key3", "value3");
        expectedMap.put("key4", "value4");

        //When
        Map<String, String> realMap = parentMapUpdater.update(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedMap, realMap);
    }
}
