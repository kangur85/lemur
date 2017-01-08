package eu.kaszkowiak.poc;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileEntryTest {

    @Test
    public void createFileEntry() {
        FileEntry fe = new FileEntry("/tmp/abc.txt", FileEntry.Type.NEW_FILE);
        assertThat(fe.getType()).isEqualTo(FileEntry.Type.NEW_FILE);
        assertThat(fe.getFilePath()).isNotNull();
    }

}
