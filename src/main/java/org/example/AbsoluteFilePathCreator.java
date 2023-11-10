package org.example;

import java.io.File;

public class AbsoluteFilePathCreator {
    private String prefixName;

    public AbsoluteFilePathCreator() {
    }

    public AbsoluteFilePathCreator(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getPath(String filePath) {
        return getDirectoryPath(filePath) + File.separator + getNewFileName(filePath);
    }

    protected String getNewFileName(String filePath) {
        return prefixName + getFileName(filePath);
    }

    protected String getDirectoryPath(String filePath) {
        File file = new File(filePath);
        return file.getParent();
    }

    protected String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }
}
