package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChildMapUpdater {
    private List<String> keysWithNoValueInParentMap = new ArrayList<>();
    private Map<String, String> newChildMap = new HashMap<>();

    public Map<String, String> createMap(Map<String, String> updatedParentMap, Map<String, String> basicLocalChildMap) {
        Map<String, String> updatedChildMap = getUpdatedChildMap(updatedParentMap, basicLocalChildMap);
        return replaceMissingKeysToTheStartOfMap(updatedChildMap);
    }

    private Map<String, String> replaceMissingKeysToTheStartOfMap(Map<String, String> updatedChildMap) {
        Map<String, String> sortedMap = new LinkedHashMap<>();
        for (String keyWithNoValueInParentMap : keysWithNoValueInParentMap) {
            sortedMap.put(keyWithNoValueInParentMap, newChildMap.get(keyWithNoValueInParentMap));
        }
        sortedMap.putAll(updatedChildMap);
        return sortedMap;
    }

    private Map<String, String> getUpdatedChildMap(Map<String, String> updatedParentMap, Map<String, String> basicLocalChildMap) {
        for (Map.Entry<String, String> entryBasicLocalChildMap : basicLocalChildMap.entrySet()) {
            String basicLocalChildMapKey = entryBasicLocalChildMap.getKey();

            if (updatedParentMap.containsKey(basicLocalChildMapKey)) {
                String updatedParentMapValue = updatedParentMap.get(basicLocalChildMapKey);
                newChildMap.put(basicLocalChildMapKey, updatedParentMapValue);
            } else {
                String newChildMapValue = getMissingValue();
                newChildMap.put(basicLocalChildMapKey, newChildMapValue);
                keysWithNoValueInParentMap.add(basicLocalChildMapKey);
            }
        }
        return newChildMap;
    }

    String getMissingValue() {
        return "YOU DON'T HAVE THE VALUE FOR THIS KYE IN PARENT LOCAL FILE";
    }


}
