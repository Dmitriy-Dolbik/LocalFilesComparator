package org.example;

import org.example.contentWriter.ContentWriter;
import org.example.contentWriter.impl.FileContentWriter;
import org.example.filePathCreators.PathCreator;
import org.example.filePathCreators.impl.BasicLocalPathCreator;
import org.example.mapConverters.FileToMapConverter;
import org.example.mapConverters.impl.FileToMapConverterImpl;
import org.example.mapUpdaters.MapUpdater;
import org.example.mapUpdaters.impl.ChildMapUpdater;
import org.example.mapUpdaters.impl.ParentMapUpdater;
import org.example.reporters.Reporter;
import org.example.reporters.impl.DifferentValuesReporter;

import java.util.HashMap;
import java.util.Map;

public class FileUpdater {
    private final ContentWriter fileContentWriter = new FileContentWriter();
    private final FileToMapConverter fileToMapConverterImpl = new FileToMapConverterImpl();
    protected String parentFilePath;
    protected String childFilePath;
    private final Reporter differentValuesReporter = new DifferentValuesReporter();
    private final PathCreator basicLocalFilePathCreator = new BasicLocalPathCreator();
    private final MapUpdater creatorUpdatedChildMap = new ChildMapUpdater();
    private final MapUpdater parentMapUpdater = new ParentMapUpdater();

    public FileUpdater() {
        //for testing
    }

    public FileUpdater(String parentFilePath, String childFilePath) {
        this.parentFilePath = parentFilePath;
        this.childFilePath = childFilePath;
    }

    public void update() {
        Map<String, String> updatedParentMap = updateParentFile();
        updateChildFile(updatedParentMap);
    }

    public Map<String, String> updateParentFile() {
        Map<String, String> updatedParentMap;
        Map<String, String> originalParentMap = fileToMapConverterImpl.convert(parentFilePath);
        Map<String, String> originalChildMap = fileToMapConverterImpl.convert(childFilePath);
        updatedParentMap = parentMapUpdater.update(originalParentMap, originalChildMap);
        fileContentWriter.writeFile(updatedParentMap, parentFilePath);
        differentValuesReporter.report(updatedParentMap, originalChildMap);
        return updatedParentMap;
    }

    public void updateChildFile(Map<String, String> updatedParentMap) {
        String childBasicLocalFilePath = basicLocalFilePathCreator.create(childFilePath);
        Map<String, String> basicLocalChildMap = fileToMapConverterImpl.convert(childBasicLocalFilePath);
        Map<String, String> newChildMap = creatorUpdatedChildMap.update(updatedParentMap, basicLocalChildMap);
        fileContentWriter.writeFile(newChildMap, childFilePath);
    }
}
