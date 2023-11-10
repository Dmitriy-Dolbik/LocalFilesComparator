package org.example;

public class BasicLocalPathCreator extends AbsoluteFilePathCreator {

    @Override
    protected String getNewFileName(String filePath) {
        String fileName = getFileName(filePath);

        int lastPointIndex = fileName.lastIndexOf(".");

        int lastUnderscoreIndex = fileName.lastIndexOf("_");
        int secondLastUnderscoreIndex = fileName.lastIndexOf("_", lastUnderscoreIndex - 1);

        return fileName.substring(0, secondLastUnderscoreIndex) + fileName.substring(lastPointIndex);
    }
}
