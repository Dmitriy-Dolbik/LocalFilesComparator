package org.example.mapConverters.impl;

import org.example.contentLoader.ContentLoader;
import org.example.contentLoader.impl.FileContentLoader;
import org.example.mapConverters.FileToMapConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.utils.Constants.EQUAL_SIGN;

public class FileToMapConverterImpl implements FileToMapConverter {
    private final ContentLoader fileContentLoader = new FileContentLoader();

    public Map<String, String> convert(String filePath) {
        List<String> fileContent = fileContentLoader.getFileContent(filePath);
        return convertFileContentToMap(fileContent);
    }

    Map<String, String> convertFileContentToMap(List<String> fileContent) {
        Map<String, String> fileContentAsMap = new HashMap<>();

        for (String contentLine : fileContent) {
            String[] keyAndValue = contentLine.split(EQUAL_SIGN);
            if (isStandardContentLine(keyAndValue)) {
                fillMapWithCorrectData(keyAndValue, fileContentAsMap);
            }
        }
        return fileContentAsMap;
    }

    private boolean isStandardContentLine(String[] keyAndValue) {
        return keyAndValue.length == 2;
    }

    private void fillMapWithCorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String key = keyAndValue[0].trim();
        String value = keyAndValue[1].trim();
        String valueInLowerCase = value.toLowerCase();
        fileContentAsMap.put(key, valueInLowerCase);
    }
}
