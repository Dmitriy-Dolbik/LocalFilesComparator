package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.example.Constants.EQUAL_SIGN;

public class ContentWriter {

    public void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileSourcePath))) {
            for (Map.Entry<String, String> entryUpdatedParentMap : contentMapToWrite.entrySet()) {
                String contentLine = entryUpdatedParentMap.getKey() + EQUAL_SIGN + entryUpdatedParentMap.getValue();
                bufferedWriter.write(contentLine);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
