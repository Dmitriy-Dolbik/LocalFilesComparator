package org.example.utils;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileReporter {
    private final ContentWriter contentWriter = new ContentWriter();
    private final String REPORT_FILE_NAME = "report file.txt";
    private final String reportFilePath = getReportFilePath();

    public String getReportFilePath() {
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + File.separator + REPORT_FILE_NAME;
    }

    public void reportAboutKeysWithDifferentValues(Map<String, String> updatedParentMap, Map<String, String> originalChildMap) {
        Map<String, String> keysWithDifferentValues = getKeysWithDifferentValues(updatedParentMap, originalChildMap);
        String fileHeader = getReportFileHeader(keysWithDifferentValues);
        contentWriter.writeFile(keysWithDifferentValues, reportFilePath, fileHeader);
    }

    private String getReportFileHeader(Map<String, String> keysWithDifferentValues) {
        return !keysWithDifferentValues.isEmpty()
                ? "Parent and child files have the different values for this keys\n\n"
                : "There no different values for the same keys in the parent and child files";
    }

    private Map<String, String> getKeysWithDifferentValues(Map<String, String> newParentFileAsMap, Map<String, String> childMap) {
        if (newParentFileAsMap.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, String> keysWithCombinedValue = new HashMap<>();
        for (Map.Entry<String, String> entryParentMap : newParentFileAsMap.entrySet()) {
            String parentKey = entryParentMap.getKey();
            String parentValue = newParentFileAsMap.get(parentKey);

            if (childMap.containsKey(parentKey)) {
                String childValue = childMap.get(parentKey);
                if (!childValue.equals(parentValue)) {
                    String combinedParentAndChildValue = getCombinedValue(parentValue, childValue);
                    keysWithCombinedValue.put(parentKey, combinedParentAndChildValue);
                }
            }
        }

        return keysWithCombinedValue;
    }

    String getCombinedValue(String parentValue, String childValue) {
        return "\n" +
                "------------" +
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
