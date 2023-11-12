package org.example.filePathCreators.impl;

import org.example.filePathCreators.PathCreator;

import java.io.File;

public abstract class FilePathCreator implements PathCreator {
    protected String getDirectoryPath(String filePath) {
        File file = new File(filePath);
        return file.getParent();
    }

    public String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }
}
