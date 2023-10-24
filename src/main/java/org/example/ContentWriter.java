package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.example.Constants.EQUAL_SIGN;

public class ContentWriter {
    private static final String PREFIX_NAME = "newVersion_";
    private final String parentFilePath;
    private final String parentFileName;
    private final String parentDirectoryPath;

    public ContentWriter(String parentFilePath) {
        this.parentFilePath = parentFilePath;
        parentFileName = getFileName();
        parentDirectoryPath = getDirectoryPath();
    }

    public void createFile(Map<String, String> updatedParentMap) {
        String updatedParentFileAbsolutPath = getUpdatedParentFileAbsolutPath();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(updatedParentFileAbsolutPath))) {
            for (Map.Entry<String, String> entryUpdatedParentMap : updatedParentMap.entrySet()) {
                String contentLine = entryUpdatedParentMap.getKey() + EQUAL_SIGN + entryUpdatedParentMap.getValue();
                bufferedWriter.write(contentLine);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUpdatedParentFileAbsolutPath() {
        return parentDirectoryPath + File.separator + getNewParentFileName();
    }

    private String getNewParentFileName() {
        return PREFIX_NAME + parentFileName;
    }

    private String getDirectoryPath() {
        File file = new File(parentFilePath);
        return file.getParent();
    }

    private String getFileName() {
        File file = new File(parentFilePath);
        return file.getName();
    }
}
