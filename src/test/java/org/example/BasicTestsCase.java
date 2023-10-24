package org.example;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicTestsCase {

    protected void assertEqualsMaps(Map<String, String> expectedMap, Map<String, String> realMap) {
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
