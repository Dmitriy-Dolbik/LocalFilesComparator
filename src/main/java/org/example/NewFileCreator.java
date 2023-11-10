package org.example;

import org.example.utils.AbsoluteFilePathCreator;
import org.example.utils.ContentWriter;
import org.example.utils.FileReporter;

import java.util.HashMap;
import java.util.Map;

public class NewFileCreator {
    private final ContentWriter contentWriter = new ContentWriter();
    private final FileToMapConverter fileToMapConverter = new FileToMapConverter();
    protected String parentFilePath;
    protected String childFilePath;
    protected Map<String, String> updatedParentMap;
    private Map<String, String> originalChildMap = new HashMap<>();
    private FileReporter fileReporter = new FileReporter();
    private final AbsoluteFilePathCreator absoluteFilePathCreator = new AbsoluteFilePathCreator();
    private final UpdatedChildMapCreator creatorUpdatedChildMap = new UpdatedChildMapCreator();


    private UpdatedParentMapCreator updatedParentMapCreator = new UpdatedParentMapCreator();

    public NewFileCreator() {
        //for testing
    }

    public NewFileCreator(String parentFilePath, String childFilePath) {
        this.parentFilePath = parentFilePath;
        this.childFilePath = childFilePath;
    }

    public void create() {
        createNewParentFile();
        createNewChildFile();
    }

    public void createNewParentFile() {
        Map<String, String> originalParentMap = fileToMapConverter.getFileContentAsMap(parentFilePath);
        originalChildMap = fileToMapConverter.getFileContentAsMap(childFilePath);
        updatedParentMap = updatedParentMapCreator.createMap(originalParentMap, originalChildMap);
        contentWriter.writeFile(updatedParentMap, parentFilePath);

        fileReporter.reportAboutKeysWithDifferentValues(updatedParentMap, originalChildMap);
    }

    public void createNewChildFile() {
        String childBasicLocalFilePath = absoluteFilePathCreator.getBasicLocalFilePath(childFilePath);
        Map<String, String> basicLocalChildMap = fileToMapConverter.getFileContentAsMap(childBasicLocalFilePath);
        Map<String, String> newChildMap = creatorUpdatedChildMap.createMap(updatedParentMap, basicLocalChildMap);
        contentWriter.writeFile(newChildMap, childFilePath);
    }
}
