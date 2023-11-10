package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Constants.EQUAL_SIGN;

public class FileToMapConverter {
    private ContentLoader contentLoader = new ContentLoader();

    public Map<String, String> getFileContentAsMap(String filePath) {
        List<String> fileContent = contentLoader.getFileContent(filePath);
        return convertFileContentToMap(fileContent);
    }

    public Map<String, String> convertFileContentToMap(List<String> fileContent) {
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
