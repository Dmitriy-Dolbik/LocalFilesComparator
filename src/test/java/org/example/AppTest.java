package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest extends LocalFilesComparator{
    public static final String KEY_1 = "key1";
    public static final String VALUE_1 = "value1";
    public static final String KEY_2 = "key2";
    public static final String VALUE_2_FROM_PARENT = "value2_FromParent";
    public static final String VALUE_2_FROM_CHILD = "value2_FromChild";
    public static final String KEY_3 = "key3";
    public static final String VALUE_3 = "value3";
    public static final String KEY_4 = "key4";
    public static final String VALUE_4 = "value4";
    Map<String, String> parentMap = new HashMap<>();
    Map<String, String> childMap = new HashMap<>();
    LocalFilesComparator localFilesComparator;

    Map<String, String> expectedUpdatedParentMap = new HashMap<>();

    @BeforeEach
    private void beforeEachTest() {
        localFilesComparator = new LocalFilesComparator();
        fillParentMap();
        fillChildMap();
        fillExpectedMap();
    }

    private void fillExpectedMap() {
        expectedUpdatedParentMap.put(KEY_1, VALUE_1);

        String combinedValue = getCombinedValue(VALUE_2_FROM_PARENT, VALUE_2_FROM_CHILD);
        expectedUpdatedParentMap.put(KEY_2, combinedValue);

        expectedUpdatedParentMap.put(KEY_3, VALUE_3);
        expectedUpdatedParentMap.put(KEY_4, VALUE_4);
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

    @Test
    public void testApp() {
        //When
        Map<String, String> realUpdatedParentmap = localFilesComparator.getUpdatedParentMap(parentMap, childMap);

        //Then
        assertEqualsMaps(expectedUpdatedParentMap, realUpdatedParentmap);
    }

    private void assertEqualsMaps(Map<String, String> expectedMap, Map<String, String> realMap) {
        assertEquals(expectedMap.size(), realMap.size());

        for (Map.Entry<String, String> entryRealMap : realMap.entrySet()) {
            String realMapKey = entryRealMap.getKey();
            assertTrue(expectedMap.containsKey(realMapKey));
            assertEqualsValues(expectedMap, realMap, realMapKey);
        }
    }

    private static void assertEqualsValues(Map<String, String> expectedMap, Map<String, String> realMap, String key) {
        assertEquals(expectedMap.get(key), realMap.get(key));
    }
}
