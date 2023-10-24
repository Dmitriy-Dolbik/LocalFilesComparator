package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.Constants.EQUAL_SIGN;

public class ContentConverterToMap {
    public static Map<String, String> convertFileContentToMap(List<String> fileContent) {
        Map<String, String> fileContentAsMap = new HashMap<>();

        for (String contentLine : fileContent) {
            String[] keyAndValue = contentLine.split(EQUAL_SIGN);
            if (isStandardContentLine(keyAndValue)) {
                fillMapWithCorrectData(keyAndValue, fileContentAsMap);
            } else {
                fillMapWithIncorrectData(keyAndValue, fileContentAsMap);
            }
        }
        return fileContentAsMap;
    }

    private static boolean isStandardContentLine(String[] keyAndValue) {
        return keyAndValue.length == 2;
    }

    private static void fillMapWithCorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String key = keyAndValue[0].trim();
        String value = keyAndValue[1].trim();
        String valueInLowerCase = value.toLowerCase();
        fileContentAsMap.put(key, valueInLowerCase);
    }

    private static void fillMapWithIncorrectData(String[] keyAndValue, Map<String, String> fileContentAsMap) {
        String incorrectContentLine = returnKeyAndValueToOriginalForm(keyAndValue);
        fileContentAsMap.put("rawLine", incorrectContentLine);
    }

    private static String returnKeyAndValueToOriginalForm(String[] keyAndValue) {
        return String.join(EQUAL_SIGN, keyAndValue);
    }
}
