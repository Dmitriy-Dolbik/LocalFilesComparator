package org.example.reporters;

import java.util.Map;

public interface Reporter {
    void report(Map<String, String> updatedParentMap, Map<String, String> originalChildMap);
}
