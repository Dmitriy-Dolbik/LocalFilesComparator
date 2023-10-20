package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalFilesComparator {
    private String parentFilePath;
    private String childFilePath;
    private String parentFileName;
    private String parentDirectoryPath;

    public LocalFilesComparator() {
        //for testing
    }

    public LocalFilesComparator(String parentFilePath, String childFilePath) {
        this.parentFilePath = parentFilePath;
        this.childFilePath = childFilePath;
        parentFileName = getFileName(parentFilePath);
        parentDirectoryPath = getDirectoryPath(parentFilePath);
    }

    private String getDirectoryPath(String filePath) {
        File file = new File(filePath);
        return file.getParent();
    }

    private String getFileName(String parentFilePath) {
        File file = new File(parentFilePath);
        return file.getName();
    }

    public void createUpdatedParentFile(String parentFilePath, String childFilePath) {
        Map<String, String> originalParentMap = getFileContentAsMap(parentFilePath);
        Map<String, String> originalChildMap = getFileContentAsMap(childFilePath);
        Map<String, String> updatedParentMap = getUpdatedParentMap(originalParentMap, originalChildMap);
        createFile(updatedParentMap);
    }

    private void createFile(Map<String, String> updatedParentMap) {
        String updatedParentFileAbsolutPath = getUpdatedParentFileAbsolutPath();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(updatedParentFileAbsolutPath))) {
            for (Map.Entry<String, String> entryUpdatedParentMap : updatedParentMap.entrySet()) {
                String contentLine = entryUpdatedParentMap.getKey() + " = " + entryUpdatedParentMap.getValue();
                bufferedWriter.write(contentLine);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUpdatedParentFileAbsolutPath() {
        return parentFilePath + File.separator + getNewParentFileName();
    }

    private String getNewParentFileName() {
        return "newVersion_" + parentFileName;
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
        Map<String, String> newParentFileAsMap = new HashMap<>(parentMap);
        addDifferentValuesFromChildMap(newParentFileAsMap, childMap);
        addNewKeysAndValuesFromChildMap(childMap, parentMap);
        return newParentFileAsMap;
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

    private boolean isStandardContentLine(String[] keyAndValue) {
        return keyAndValue.length == 2;
    }

    private void fillMapWithCorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String key = keyAndValue[0];
        String value = keyAndValue[1];
        fileContentAsMap.put(key, value);
    }

    private void fillMapWithIncorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String incorrectContentLine = returnKeyAndValueToOriginalForm(keyAndValue);
        fileContentAsMap.put("rawLine", incorrectContentLine);
    }

    private String returnKeyAndValueToOriginalForm(String[] keyAndValue) {
        return String.join("=", keyAndValue);
    }

    private void addDifferentValuesFromChildMap(Map<String, String> newParentFileAsMap, Map<String, String> childMap) {
        for (Map.Entry<String, String> entryParentMap : newParentFileAsMap.entrySet()) {
            String parentKey = entryParentMap.getKey();
            String parentValue = newParentFileAsMap.get(parentKey);

            if (childMap.containsKey(parentKey)) {
                String childValue = childMap.get(parentKey);
                if (!childValue.equals(parentValue)) {
                    String combinedParentAndChildValue = getCombinedValue(parentValue, childValue);
                    updateValue(newParentFileAsMap, combinedParentAndChildValue, parentKey);
                }


            }
        }
    }

    private void updateValue(Map<String, String> newParentFileAsMap, String combinedParentAndChildValue, String parentKey) {
        newParentFileAsMap.put(parentKey, combinedParentAndChildValue);
    }

    protected String getCombinedValue(String parentValue, String childValue) {
        String combinedValue = "\n" +
                "------------" +
                "CHOOSE CORRECT VALUE" +
                "\n" +
                "parent value = " +
                parentValue +
                "\n" +
                "childValue = " +
                childValue +
                "\n" +
                "------------";
        return combinedValue;
    }

    private void addNewKeysAndValuesFromChildMap(Map<String, String> childMap, Map<String, String> parentMap) {
        for (Map.Entry<String, String> entryChildMap : childMap.entrySet()) {
            String childKey = entryChildMap.getKey();
            String childValue = entryChildMap.getValue();

            if (!parentMap.containsKey(childKey)) {
                parentMap.put(childKey, childValue);
            }
        }
    }

    private void deleteParentKeysAndValuesFromChild(Map<String, String> newParentFileAsMap, Map<String, String> childMap) {
        for (Map.Entry<String, String> entryParentMap : newParentFileAsMap.entrySet()) {
            String parentKey = entryParentMap.getKey();
            childMap.remove(parentKey);
        }
    }
}
