package org.example;

import java.util.Map;

public class CreatorNewChildFile extends CreatorNewParentFile {
    private final BasicLocalPathCreator basicLocalPathCreator = new BasicLocalPathCreator();
    private final AbsoluteFilePathCreator absoluteFilePathCreator = new AbsoluteFilePathCreator("newVersion_");
    private final CreatorNewChildMap creatorNewChildMap = new CreatorNewChildMap();
    private final FileToMapConverter fileToMapConverter = new FileToMapConverter();
    private final ContentWriter contentWriter = new ContentWriter();


    public CreatorNewChildFile(String parentFilePath, String childFilePath) {
        super(parentFilePath, childFilePath);
    }

    public static void main(String[] args) {
        String parentFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/newVersion_res-visualisation-customer_be_BY.properties";
        String childFilePath = "/home/ddolbik/IdeaProjects/comparingLocalFiles/src/main/resources/res-visualisation-scokiosk_be_BY.properties";

        CreatorNewChildFile creatorNewChildFile = new CreatorNewChildFile(parentFilePath, childFilePath);
        creatorNewChildFile.createNewChildFile();
    }

    public void createNewChildFile() {
        String newChildFilePath = absoluteFilePathCreator.getPath(childFilePath);
        String childBasicLocalFilePath = basicLocalPathCreator.getPath(childFilePath);
        Map<String, String> basicLocalChildMap = fileToMapConverter.getFileContentAsMap(childBasicLocalFilePath);
        Map<String, String> updatedParentMap = fileToMapConverter.getFileContentAsMap(parentFilePath);
        Map<String, String> newChildMap = creatorNewChildMap.createMap(updatedParentMap, basicLocalChildMap);
        contentWriter.writeFile(newChildMap, newChildFilePath);
    }
}
