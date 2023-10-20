package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LocalFilesComparator {
    private String parentFilePath;
    private String childFilePath;

    public LocalFilesComparator(String parentFilePath, String childFilePath) {
        this.parentFilePath = parentFilePath;
        this.childFilePath = childFilePath;
    }

    public Map<String, String> getUpdatedParentMap(String parentFilePath, String childFilePath) {
        Map<String, String> originalParentMap = getFileContentAsMap(parentFilePath);
        Map<String, String> originalChildMap = getFileContentAsMap(childFilePath);
        return getUpdatedParentMap(originalParentMap, originalChildMap);
    }


    private Map<String, String> getFileContentAsMap(String fileStringPath) {
        Path filePath = Paths.get(fileStringPath);
        try {
            return getFileContentAsMap(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getUpdatedParentMap(Map<String, String> parentMap, Map<String, String> childMap) {
        Map<String, String> updatedParentMap = new HashMap<>();

        Set<Map.Entry<String, String>> parentMapEntrySet = parentMap.entrySet();
        for (Map.Entry<String, String> entry : parentMapEntrySet) {
            String parentKey = entry.getKey();
            if (childMap.containsKey(parentKey)) {
                String childValue = childMap.get(parentKey);
                String parentValue = parentMap.get(parentKey);
                if (childValue.equals(parentValue)) {
                    updatedParentMap.put(parentKey, parentValue);
                    childMap.remove(parentKey);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("\n")
                            .append("------------")
                            .append("CHOOSE CORRECT VALUE")
                            .append("\n")
                            .append("parent value = ")
                            .append(parentValue)
                            .append("\n")
                            .append("childValue = ")
                            .append(childValue)
                            .append("\n")
                            .append("------------");
                    String combinedParentAndChildValue = stringBuilder.toString();
                    updatedParentMap.put(parentKey, combinedParentAndChildValue);
                    childMap.remove(parentKey);
                }
            }
        }

        Set<String> childKeySet = childMap.keySet();

        Iterator<String> childKeySetIterator = childKeySet.iterator();
        while (childKeySetIterator.hasNext()) {
            String childKey = childKeySetIterator.next();
            String childValue = childMap.get(childKey);
            updatedParentMap.put(childKey, childValue);
            childMap.remove(childKey);
        }

        return updatedParentMap;
    }

    private Map<String, String> getFileContentAsMap(Path filePath) throws IOException {
        List<String> fileContent = Files.readAllLines(filePath);
        return convertFileContentToMap(fileContent);
    }

    private Map<String, String> convertFileContentToMap(List<String> fileContent) {
        Map<String, String> fileContentAsMap = new HashMap<>();
        for (String contentLine : fileContent) {
            String[] keyAndValue = contentLine.split("=");
            if (isStandardContentLine(keyAndValue)) {
                fillMapWithCorrectData(keyAndValue, fileContentAsMap);
            } else {
                fillMapWithIncorrectData(keyAndValue, fileContentAsMap);
            }
        }
        return fileContentAsMap;
    }

    private void fillMapWithIncorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String originalLine = returnKeyAndValueToOriginalForm(keyAndValue);
        fileContentAsMap.put("rawLine = ", originalLine);
    }

    private void fillMapWithCorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String key = keyAndValue[0];
        String value = keyAndValue[1];
        fileContentAsMap.put(key, value);
    }

    private String returnKeyAndValueToOriginalForm(String[] keyAndValue) {
        return String.join("=", keyAndValue);
    }

    private boolean isStandardContentLine(String[] keyAndValue) {
        return keyAndValue.length == 2;
    }


}
