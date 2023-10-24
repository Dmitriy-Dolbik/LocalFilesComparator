package org.example;

import java.util.SortedMap;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String patentFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-customer_be_BY.properties";
        String childFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";

        LocalFilesComparator localFilesComparator = new LocalFilesComparator(patentFilePath, childFilePath);

        localFilesComparator.createUpdatedParentFile();
    }
}
