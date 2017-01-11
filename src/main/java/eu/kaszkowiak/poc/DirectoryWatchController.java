package eu.kaszkowiak.poc;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.Iterator;

@Controller
public class DirectoryWatchController {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/lemur")
    @SendTo("/topic/files")
    public void getAll() {
        Flux<FileEntry> o = Flux.concat(getDirectoryContents(), getDirectoryChanges());
        o.subscribe(entry -> template.convertAndSend("/topic/files", entry));
    }

    private Flux<FileEntry> getDirectoryChanges() {
        return Flux.empty();
    }

    private Flux<FileEntry> getDirectoryContents() {
        return Flux.fromIterable(new Iterable<FileEntry>() {

            private Iterator<FileEntry> iterator;

            private int cnt = 0;

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
