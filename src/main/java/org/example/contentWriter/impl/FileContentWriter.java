package org.example.contentWriter.impl;

import org.example.contentWriter.ContentWriter;
import org.example.exceptions.WritingFileException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import static org.example.utils.Constants.EQUAL_SIGN;

public class FileContentWriter implements ContentWriter {

    public void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileSourcePath))) {
            write(contentMapToWrite, bufferedWriter);
        } catch (IOException e) {
            throwException(e);
        }
    }

    public void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath, String header) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileSourcePath))) {
            bufferedWriter.write(header);
            write(contentMapToWrite, bufferedWriter);
        } catch (IOException e) {
            throwException(e);
        }
    }

    private void write(Map<String, String> contentMapToWrite, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, String> entryUpdatedParentMap : contentMapToWrite.entrySet()) {
            String contentLine = entryUpdatedParentMap.getKey() + EQUAL_SIGN + entryUpdatedParentMap.getValue();
            bufferedWriter.write(contentLine);
            bufferedWriter.newLine();
        }
    }

    private void throwException(IOException e) {
        throw new WritingFileException(String.format("Error during writing file content, message: [%s]", e.getMessage()));
    }
}
