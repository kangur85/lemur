package eu.kaszkowiak.poc.domain.model;

import eu.kaszkowiak.poc.domain.model.FileEntry;
import eu.kaszkowiak.poc.domain.model.FileEntryType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileEntryTest {

    @Test
    public void createFileEntry() {
        FileEntry fe = new FileEntry("/tmp/abc.txt", FileEntryType.EXISTING_FILE);
        assertThat(fe.getType()).isEqualTo(FileEntryType.EXISTING_FILE);
    }

    @Test
    public void fileEntryToString() {
        FileEntry fe = new FileEntry("/tmp/abc.txt", FileEntryType.EXISTING_FILE);
        assertThat(fe.toString()).isEqualTo("{\"filePath\":\"/tmp/abc.txt\",\"type\":\"existing file\"}");
    }

}
