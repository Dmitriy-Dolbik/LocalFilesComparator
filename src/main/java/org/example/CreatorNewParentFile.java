package org.example;

import java.util.Map;

public class CreatorNewParentFile {
    private final ContentWriter contentWriter = new ContentWriter();
    private final FileToMapConverter fileToMapConverter = new FileToMapConverter();
    private final AbsoluteFilePathCreator absoluteFilePathCreator = new AbsoluteFilePathCreator("newVersion_");
    protected String parentFilePath;
    protected String childFilePath;
    protected Map<String, String> updatedParentMap;


    private CreatorNewParentMap creatorNewParentMap = new CreatorNewParentMap();

    public CreatorNewParentFile() {
        //for testing
    }

    public CreatorNewParentFile(String parentFilePath, String childFilePath) {
        this.parentFilePath = parentFilePath;
        this.childFilePath = childFilePath;
    }

    public static void main(String[] args) {
        String childFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";
        String patentFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-customer_be_BY.properties";

        CreatorNewParentFile creatorNewParentFile = new CreatorNewParentFile(patentFilePath, childFilePath);

        creatorNewParentFile.createNewParentFile();
    }

    public void createNewParentFile() {
        Map<String, String> originalParentMap = fileToMapConverter.getFileContentAsMap(parentFilePath);
        Map<String, String> originalChildMap = fileToMapConverter.getFileContentAsMap(childFilePath);
        updatedParentMap = creatorNewParentMap.getUpdatedParentMap(originalParentMap, originalChildMap);
        String updatedParentFilePath = absoluteFilePathCreator.getPath(parentFilePath);
        contentWriter.writeFile(updatedParentMap, updatedParentFilePath);
    }
}
