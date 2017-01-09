package eu.kaszkowiak.poc;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public enum FileEntryType {

    NEW_FILE;

    private static Map<String, FileEntryType> texts = new HashMap(1);

    static {
        texts.put("new file", NEW_FILE);
    }

    @JsonValue
    @Override
    public String toString() {
        for (Map.Entry<String, FileEntryType> entry: texts.entrySet()) {
            if (this.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // not going to happen
    }

}
