package org.example;

import java.util.List;
import java.util.Map;

public class LocalFilesComparator {
    private ContentLoader contentLoader;
    private ContentWriter contentWriter;
    private String parentFilePath;
    private String childFilePath;
    
    private CreatorNewParentMap creatorNewParentMap = new CreatorNewParentMap();

    public LocalFilesComparator() {
        //for testing
    }

    public LocalFilesComparator(String parentFilePath, String childFilePath) {
        contentLoader = new ContentLoader();
        contentWriter = new ContentWriter(parentFilePath);
        this.parentFilePath = parentFilePath;
        this.childFilePath = childFilePath;
    }

    public void createUpdatedParentFile() {
        Map<String, String> originalParentMap = getFileContentAsMap(parentFilePath);
        Map<String, String> originalChildMap = getFileContentAsMap(childFilePath);
        Map<String, String> updatedParentMap = creatorNewParentMap.getUpdatedParentMap(originalParentMap, originalChildMap);
        contentWriter.createFile(updatedParentMap);
    }

    private Map<String, String> getFileContentAsMap(String filePath) {
        List<String> fileContent = contentLoader.getFileContent(filePath);
        return ContentConverterToMap.convertFileContentToMap(fileContent);
    }

}
