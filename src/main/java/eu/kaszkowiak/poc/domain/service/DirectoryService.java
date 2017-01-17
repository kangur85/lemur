package eu.kaszkowiak.poc.domain.service;

import eu.kaszkowiak.poc.domain.model.FileEntry;
import eu.kaszkowiak.poc.domain.model.FileEntryType;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.Iterator;

/**
 * Created by kan on 17.01.17.
 */
@Service
public class DirectoryService {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    public Flux<FileEntry> getDirectoryContents() {
        return Flux.fromIterable(new Iterable<FileEntry>() {

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


    public Flux<FileEntry> getDirectoryChanges() {
        return Flux.empty();
    }
}
