package org.example.mapConverters;

import java.util.List;
import java.util.Map;

public interface FileToMapConverter {
    Map<String, String> convert(String filePath);
}
