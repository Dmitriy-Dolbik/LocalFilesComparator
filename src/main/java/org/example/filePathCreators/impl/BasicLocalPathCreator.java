package org.example.filePathCreators.impl;

import java.io.File;

public class BasicLocalPathCreator extends FilePathCreator {

    public BasicLocalPathCreator() {
    }

    public String create(String filePath) {
        return getDirectoryPath(filePath) + File.separator + getBasicLocalFileName(filePath);
    }

    String getBasicLocalFileName(String filePath) {
        String fileName = getFileName(filePath);
        int lastPointIndex = fileName.lastIndexOf(".");
        int lastUnderscoreIndex = fileName.lastIndexOf("_");
        int secondLastUnderscoreIndex = fileName.lastIndexOf("_", lastUnderscoreIndex - 1);
        return fileName.substring(0, secondLastUnderscoreIndex) + fileName.substring(lastPointIndex);
    }
}