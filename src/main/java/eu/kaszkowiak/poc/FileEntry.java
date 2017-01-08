package eu.kaszkowiak.poc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class FileEntry {

    @Getter
    private final String filePath;

    @Getter
    private final Type type;

    enum Type {
        NEW_FILE
    }

}
