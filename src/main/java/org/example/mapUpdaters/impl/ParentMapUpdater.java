package org.example.mapUpdaters.impl;

import org.example.mapUpdaters.MapUpdater;

import java.util.HashMap;
import java.util.Map;

public class ParentMapUpdater implements MapUpdater {

    public Map<String, String> update(Map<String, String> parentMap, Map<String, String> childMap) {
        return addNewKeysAndValuesFromChildMap(parentMap, childMap);
    }

    private Map<String, String> addNewKeysAndValuesFromChildMap(Map<String, String> newParentFileAsMap, Map<String, String> childMap) {
        for (Map.Entry<String, String> entryChildMap : childMap.entrySet()) {
            String childKey = entryChildMap.getKey();
            String childValue = entryChildMap.getValue();

            if (!newParentFileAsMap.containsKey(childKey)) {
                newParentFileAsMap.put(childKey, childValue.toLowerCase());
            }
        }
        return newParentFileAsMap;
    }
}
