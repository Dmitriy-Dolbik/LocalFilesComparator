package org.example;

import org.example.utils.FileCopier;

public class App {
    public static void main(String[] args) {
        String parentFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-customer_be_BY.properties";
        String childFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";

        FileCopier fileCopier = new FileCopier();
        String newDirectoryName = "/copies of the original files";
        fileCopier.copyFile(parentFilePath, newDirectoryName);
        fileCopier.copyFile(childFilePath, newDirectoryName);

        NewFileCreator creatorNewFile = new NewFileCreator(parentFilePath, childFilePath);
        creatorNewFile.create();
    }
}
