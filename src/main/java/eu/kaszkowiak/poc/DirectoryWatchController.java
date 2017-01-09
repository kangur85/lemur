package eu.kaszkowiak.poc;

import io.reactivex.Observable;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Iterator;

@RestController
public class DirectoryWatchController {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    @MessageMapping("/all")
    public Observable<FileEntry> getAll() {
        return Observable.concat(getDirectoryContents(), getDirectoryChanges());
    }

    private Observable<FileEntry> getDirectoryChanges() {
        return Observable.empty();
    }

    private Observable<FileEntry> getDirectoryContents() {
        return Observable.fromIterable(new Iterable<FileEntry>() {

            private Iterator<FileEntry> iterator;

            @Override
            public Iterator<FileEntry> iterator() {

                if (iterator == null) {
                    iterator = new Iterator<FileEntry>() {
                        private Iterator<File> fileIterator = FileUtils.iterateFiles(new File(watchDirectoryPath), null, true);

                        @Override
                        public boolean hasNext() {
                            return fileIterator.hasNext();
                        }

                        @Override
                        public FileEntry next() {
                            return new FileEntry(fileIterator.next().getPath(), FileEntryType.NEW_FILE);
                        }
                    };
                }
                return iterator;
            }
        });
    }

}
