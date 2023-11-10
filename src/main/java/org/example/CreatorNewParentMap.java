package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CreatorNewParentMap {
    private List<String> keysWithCombinedValue = new ArrayList<>();
    private Map<String, String> newParentFileAsMap;


    public Map<String, String> getUpdatedParentMap(Map<String, String> parentMap, Map<String, String> childMap) {
        newParentFileAsMap = new HashMap<>(parentMap);
        addNewKeysAndValuesFromChildMap(childMap);
        addDifferentValuesFromChildMap(childMap);
        return replaceCombainedValuesToTheStartOfMap();
    }

    private Map<String, String> replaceCombainedValuesToTheStartOfMap() {
        Map<String, String> sortedMap = new LinkedHashMap<>();
        for (String keyWithCombinedValue : keysWithCombinedValue) {
            String combinedValue = newParentFileAsMap.get(keyWithCombinedValue);
            sortedMap.put(keyWithCombinedValue, combinedValue);
        }
        sortedMap.putAll(newParentFileAsMap);
        newParentFileAsMap = sortedMap;
        return newParentFileAsMap;
    }

    private void replaceCombainedValuesToTheEndOfMap(Map<String, String> newParentFileAsMap) {
        for (String keyWithCombinedValue : keysWithCombinedValue) {
            String combinedValue = newParentFileAsMap.get(keyWithCombinedValue);
            newParentFileAsMap.remove(keyWithCombinedValue);
            newParentFileAsMap.put(keyWithCombinedValue, combinedValue);
        }
    }

    private void addDifferentValuesFromChildMap(Map<String, String> childMap) {
        for (Map.Entry<String, String> entryParentMap : newParentFileAsMap.entrySet()) {
            String parentKey = entryParentMap.getKey();
            String parentValue = newParentFileAsMap.get(parentKey);

            if (childMap.containsKey(parentKey)) {
                String childValue = childMap.get(parentKey);
                if (!childValue.equals(parentValue)) {
                    String combinedParentAndChildValue = getCombinedValue(parentValue, childValue);
                    updateValue(combinedParentAndChildValue, parentKey);
                    keysWithCombinedValue.add(parentKey);
                }
            }
        }
    }

    private void addNewKeysAndValuesFromChildMap(Map<String, String> childMap) {
        for (Map.Entry<String, String> entryChildMap : childMap.entrySet()) {
            String childKey = entryChildMap.getKey();
            String childValue = entryChildMap.getValue();

            if (!newParentFileAsMap.containsKey(childKey)) {
                newParentFileAsMap.put(childKey, childValue.toLowerCase());
            }
        }
    }

    private void updateValue(String combinedParentAndChildValue, String parentKey) {
        newParentFileAsMap.put(parentKey, combinedParentAndChildValue);
    }

    String getCombinedValue(String parentValue, String childValue) {
        return "\n" +
                "------------" +
                "\n" +
                "CHOOSE CORRECT VALUE" +
                "\n" +
                "parentValue = " +
                parentValue +
                "\n" +
                "childValue  = " +
                childValue +
                "\n" +
                "------------";
    }
}
