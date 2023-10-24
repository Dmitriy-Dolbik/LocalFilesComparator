package org.example;

import java.util.HashMap;
import java.util.Map;

public class CreatorNewParentMap {

    public Map<String, String> getUpdatedParentMap(Map<String, String> parentMap, Map<String, String> childMap) {
        Map<String, String> newParentFileAsMap = new HashMap<>(parentMap);
        addDifferentValuesFromChildMap(newParentFileAsMap, childMap);
        addNewKeysAndValuesFromChildMap(childMap, newParentFileAsMap);
        return newParentFileAsMap;
    }

    private void addDifferentValuesFromChildMap(Map<String, String> newParentFileAsMap, Map<String, String> childMap) {
        for (Map.Entry<String, String> entryParentMap : newParentFileAsMap.entrySet()) {
            String parentKey = entryParentMap.getKey();
            String parentValue = newParentFileAsMap.get(parentKey);
            String parentValueInLowerCase = parentValue.toLowerCase();

            if (childMap.containsKey(parentKey)) {
                String childValueInLowerCase = childMap.get(parentKey).toLowerCase();
                if (!childValueInLowerCase.equals(parentValueInLowerCase)) {
                    String combinedParentAndChildValue = getCombinedValue(parentValueInLowerCase, childValueInLowerCase);
                    updateValue(newParentFileAsMap, combinedParentAndChildValue, parentKey);
                } else {
                    updateValue(newParentFileAsMap, parentValueInLowerCase, parentKey);
                }
            } else {
                updateValue(newParentFileAsMap, parentValueInLowerCase, parentKey);
            }
        }
    }

    private void addNewKeysAndValuesFromChildMap(Map<String, String> childMap, Map<String, String> parentMap) {
        for (Map.Entry<String, String> entryChildMap : childMap.entrySet()) {
            String childKey = entryChildMap.getKey();
            String childValue = entryChildMap.getValue();

            if (!parentMap.containsKey(childKey)) {
                parentMap.put(childKey, childValue.toLowerCase());
            }
        }
    }

    private void updateValue(Map<String, String> newParentFileAsMap, String combinedParentAndChildValue, String parentKey) {
        newParentFileAsMap.put(parentKey, combinedParentAndChildValue);
    }

    protected String getCombinedValue(String parentValue, String childValue) {
        return  "\n" +
                "------------" +
                "\n" +
                "CHOOSE CORRECT VALUE" +
                "\n" +
                "parentValue = " +
                parentValue +
                "\n" +
                "childValue = " +
                childValue +
                "\n" +
                "------------";
    }

}
