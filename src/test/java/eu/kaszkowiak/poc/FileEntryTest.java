package eu.kaszkowiak.poc;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileEntryTest {

    @Test
    public void createFileEntry() {
        FileEntry fe = new FileEntry("/tmp/abc.txt", FileEntryType.NEW_FILE);
        assertThat(fe.getType()).isEqualTo(FileEntryType.NEW_FILE);
    }

    @Test
    public void fileEntryToString() {
        FileEntry fe = new FileEntry("/tmp/abc.txt", FileEntryType.NEW_FILE);
        assertThat(fe.toString()).isEqualTo("{\"filePath\":\"/tmp/abc.txt\",\"type\":\"new file\"}");
    }

}
