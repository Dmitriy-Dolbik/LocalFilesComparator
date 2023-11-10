package org.example.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCopier {
    private AbsoluteFilePathCreator absoluteFilePathCreator = new AbsoluteFilePathCreator();

    public void copyFile(String sourcePath, String newDirectoryName) {
        String destinationDirectoryPath = getDestinationDirectoryPath(newDirectoryName);
        createNewDirectoryIfItDoesNotExist(destinationDirectoryPath);
        String destinationFilePath = getDestinationFilePath(sourcePath, destinationDirectoryPath);
        try {
            Files.copy(Paths.get(sourcePath), Paths.get(destinationFilePath));
        } catch (IOException e) {
            String messageToPrint = String.format("File with path %s already exists", destinationFilePath);
            System.out.println(messageToPrint);
        }
    }

    public String getDestinationDirectoryPath(String newDirectoryName) {
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + newDirectoryName;
    }

    private void createNewDirectoryIfItDoesNotExist(String newDirectoryPath) {
        File newDirectory = new File(newDirectoryPath);
        if (!newDirectory.exists()) {
            newDirectory.mkdir();
        }
    }

    private String getDestinationFilePath(String sourcePath, String destinationDirectoryPath) {
        String sourceFileName = absoluteFilePathCreator.getFileName(sourcePath);
        return destinationDirectoryPath + File.separator + sourceFileName;
    }
}
