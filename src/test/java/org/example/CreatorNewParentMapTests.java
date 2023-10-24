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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatorNewParentMapTests extends BasicTestsCase {
    Map<String, String> parentMap = new HashMap<>();
    Map<String, String> childMap = new HashMap<>();
    CreatorNewParentMap creatorNewParentMap;

    Map<String, String> expectedUpdatedParentMap = new HashMap<>();

    @BeforeEach
    public void beforeEachTest() {
        creatorNewParentMap = new CreatorNewParentMap();
        fillParentMap();
        fillChildMap();
        fillExpectedMap();
    }

    private void fillParentMap() {
        parentMap.put(KEY_1, VALUE_1);
        parentMap.put(KEY_2, VALUE_2_FROM_PARENT);
        parentMap.put(KEY_4, VALUE_4);
    }

    private void fillChildMap() {
        childMap.put(KEY_1, VALUE_1);
        childMap.put(KEY_2, VALUE_2_FROM_CHILD);
        childMap.put(KEY_3, VALUE_3);
    }

    private void fillExpectedMap() {
        expectedUpdatedParentMap.put(KEY_1, VALUE_1);

        String combinedValue = creatorNewParentMap.getCombinedValue(VALUE_2_FROM_PARENT, VALUE_2_FROM_CHILD);
        expectedUpdatedParentMap.put(KEY_2, combinedValue);

        expectedUpdatedParentMap.put(KEY_3, VALUE_3);
        expectedUpdatedParentMap.put(KEY_4, VALUE_4);
    }

    @Test
    public void getUpdatedParentMapTest_shouldReturnCorrectMap() {
        //When
        Map<String, String> realUpdatedParentmap = creatorNewParentMap.getUpdatedParentMap(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedUpdatedParentMap, realUpdatedParentmap);
    }
}
