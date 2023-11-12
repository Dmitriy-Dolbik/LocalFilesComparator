package org.example.mapUpdaters.impl;

import org.example.mapUpdaters.MapUpdater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChildMapUpdater implements MapUpdater {
    private final List<String> keysWithNoValueInParentMap = new ArrayList<>();

    public Map<String, String> update(Map<String, String> updatedParentMap, Map<String, String> basicLocalChildMap) {
        Map<String, String> updatedChildMap = getUpdatedChildMap(updatedParentMap, basicLocalChildMap);
        return replaceMissingKeysToTheStartOfMap(updatedChildMap);
    }

    private Map<String, String> replaceMissingKeysToTheStartOfMap(Map<String, String> updatedChildMap) {
        Map<String, String> sortedMap = new LinkedHashMap<>();
        for (String keyWithNoValueInParentMap : keysWithNoValueInParentMap) {
            sortedMap.put(keyWithNoValueInParentMap, updatedChildMap.get(keyWithNoValueInParentMap));
        }
        sortedMap.putAll(updatedChildMap);
        return sortedMap;
    }

    private Map<String, String> getUpdatedChildMap(Map<String, String> updatedParentMap, Map<String, String> basicLocalChildMap) {
        Map<String, String> updatedChildMap = new HashMap<>();
        for (Map.Entry<String, String> entryBasicLocalChildMap : basicLocalChildMap.entrySet()) {
            String basicLocalChildMapKey = entryBasicLocalChildMap.getKey();

            if (updatedParentMap.containsKey(basicLocalChildMapKey)) {
                String updatedParentMapValue = updatedParentMap.get(basicLocalChildMapKey);
                updatedChildMap.put(basicLocalChildMapKey, updatedParentMapValue);
            } else {
                String newChildMapValue = getMissingValue();
                updatedChildMap.put(basicLocalChildMapKey, newChildMapValue);
                keysWithNoValueInParentMap.add(basicLocalChildMapKey);
            }
        }
        return updatedChildMap;
    }

    String getMissingValue() {
        return "YOU DON'T HAVE THE VALUE FOR THIS KYE IN PARENT LOCAL FILE";
    }


}
