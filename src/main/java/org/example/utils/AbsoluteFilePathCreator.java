package org.example.utils;

import java.io.File;

public class AbsoluteFilePathCreator {

    public AbsoluteFilePathCreator() {
    }

    public String getBasicLocalFilePath(String filePath) {
        return getDirectoryPath(filePath) + File.separator + getBasicLocalFileName(filePath);
    }

    public String getBasicLocalFileName(String filePath) {
        String fileName = getFileName(filePath);
        int lastPointIndex = fileName.lastIndexOf(".");
        int lastUnderscoreIndex = fileName.lastIndexOf("_");
        int secondLastUnderscoreIndex = fileName.lastIndexOf("_", lastUnderscoreIndex - 1);
        return fileName.substring(0, secondLastUnderscoreIndex) + fileName.substring(lastPointIndex);
    }

    public String getDirectoryPath(String filePath) {
        File file = new File(filePath);
        return file.getParent();
    }

    public String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }
}