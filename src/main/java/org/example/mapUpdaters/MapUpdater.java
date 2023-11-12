package org.example.mapUpdaters;

import java.util.Map;

public interface MapUpdater {
    Map<String, String> update(Map<String, String> parentMap, Map<String, String> childMap);
}
