package org.example.contentWriter;

import java.util.Map;

public interface ContentWriter {
    void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath);
    void writeFile(Map<String, String> contentMapToWrite, String fileSourcePath, String header);
}
