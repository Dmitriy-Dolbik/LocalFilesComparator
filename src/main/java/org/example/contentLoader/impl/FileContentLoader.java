package org.example.contentLoader.impl;

import org.example.contentLoader.ContentLoader;
import org.example.exceptions.ReadingFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileContentLoader implements ContentLoader {

    public List<String> getFileContent(String fileStringPath) {
        Path filePath = Paths.get(fileStringPath);
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadingFileException(String.format("Error during reading file content, message: [%s]", e.getMessage()));
        }
    }
}
