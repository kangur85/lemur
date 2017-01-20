package eu.kaszkowiak.poc.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum FileEntryType {

    EXISTING_FILE;

    private static Map<String, FileEntryType> texts = new HashMap<>(1);

    static {
        texts.put("existing file", EXISTING_FILE);
    }

    @JsonValue
    @Override
    public String toString() {
        for (Map.Entry<String, FileEntryType> entry : texts.entrySet()) {
            if (this.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // not going to happen
    }

}
