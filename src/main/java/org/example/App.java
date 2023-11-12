package org.example;

import org.example.contentCopiers.ContentCopier;
import org.example.contentCopiers.impl.FileCopier;

public class App {
    public static void main(String[] args) {
        String parentFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-customer_be_BY.properties";
        String childFilePath =  "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";

        ContentCopier fileCopier = new FileCopier();
        String newDirectoryName = "/copies of the original files";
        fileCopier.copy(parentFilePath, newDirectoryName);
        fileCopier.copy(childFilePath, newDirectoryName);

        FileUpdater fileUpdater = new FileUpdater(parentFilePath, childFilePath);
        fileUpdater.update();
    }
}
