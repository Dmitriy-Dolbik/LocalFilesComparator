package org.example.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.example.utils.Constants.EQUAL_SIGN;

public class ContentWriter {

    public void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileSourcePath))) {
            write(contentMapToWrite, bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath, String header) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileSourcePath))) {
            bufferedWriter.write(header);
            write(contentMapToWrite, bufferedWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(Map<String, String> contentMapToWrite, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, String> entryUpdatedParentMap : contentMapToWrite.entrySet()) {
            String contentLine = entryUpdatedParentMap.getKey() + EQUAL_SIGN + entryUpdatedParentMap.getValue();
            bufferedWriter.write(contentLine);
            bufferedWriter.newLine();
        }
    }
}
