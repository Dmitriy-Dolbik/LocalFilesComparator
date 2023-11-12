package org.example.contentCopiers.impl;

import org.example.contentCopiers.ContentCopier;
import org.example.filePathCreators.PathCreator;
import org.example.filePathCreators.impl.BasicLocalPathCreator;
import org.example.filePathCreators.impl.FilePathCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCopier implements ContentCopier {
    private final FilePathCreator basicLocalFilePathCreator = new BasicLocalPathCreator();

    public void copy(String sourceFilePath, String newDirectoryName) {
        String destinationDirectoryPath = getDestinationDirectoryPath(newDirectoryName);
        createNewDirectoryIfItDoesNotExist(destinationDirectoryPath);
        String destinationFilePath = getDestinationFilePath(sourceFilePath, destinationDirectoryPath);
        try {
            Files.copy(Paths.get(sourceFilePath), Paths.get(destinationFilePath));
        } catch (IOException e) {
            String messageToPrint = String.format("File with path %s was not copied, because it already exists", destinationFilePath);
            System.out.println(messageToPrint);
        }
    }

    private String getDestinationDirectoryPath(String newDirectoryName) {
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + newDirectoryName;
    }

    private void createNewDirectoryIfItDoesNotExist(String newDirectoryPath) {
        File newDirectory = new File(newDirectoryPath);
        if (!newDirectory.exists()) {
            newDirectory.mkdir();
        }
    }

    private String getDestinationFilePath(String sourceFilePath, String destinationDirectoryPath) {
        String sourceFileName = basicLocalFilePathCreator.getFileName(sourceFilePath);
        return destinationDirectoryPath + File.separator + sourceFileName;
    }
}