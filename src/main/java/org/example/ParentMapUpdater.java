package org.example;

import org.example.utils.ContentWriter;

import java.util.HashMap;
import java.util.Map;

public class ParentMapUpdater {
    private Map<String, String> newParentFileAsMap = new HashMap<>();
    private ContentWriter contentWriter = new ContentWriter();


    public Map<String, String> createMap(Map<String, String> parentMap, Map<String, String> childMap) {
        newParentFileAsMap.putAll(parentMap);
        addNewKeysAndValuesFromChildMap(newParentFileAsMap, childMap);
        return newParentFileAsMap;
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

    private void updateValue(String combinedParentAndChildValue, String parentKey) {
        newParentFileAsMap.put(parentKey, combinedParentAndChildValue);
    }
}
