package eu.kaszkowiak.poc;

import io.reactivex.Observable;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.File;
import java.util.Iterator;

@Controller
@CrossOrigin(origins = "*")
public class DirectoryWatchController {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;


    @MessageMapping("/lemur")
    @SendTo("/topic/files")
    public DeferredResult<FileEntry> getAll() {
        Observable<FileEntry> o = Observable.concat(getDirectoryContents(), getDirectoryChanges());
        DeferredResult<FileEntry> deferred = new DeferredResult<FileEntry>();
        o.subscribe(m -> deferred.setResult(m), e -> deferred.setErrorResult(e));
        return deferred;
    }

    private Observable<FileEntry> getDirectoryChanges() {
        return Observable.empty();
    }

    private Observable<FileEntry> getDirectoryContents() {
        return Observable.fromIterable(new Iterable<FileEntry>() {

            private Iterator<FileEntry> iterator;

            private int cnt = 0;

            @Override
            public Iterator<FileEntry> iterator() {

                if (iterator == null) {
                    iterator = new Iterator<FileEntry>() {
                        private Iterator<File> fileIterator = FileUtils.iterateFiles(new File(watchDirectoryPath), null, true);

                        @Override
                        public boolean hasNext() {
                            if (fileIterator.hasNext()) {
                                cnt++;
                            }
                            else {
                                System.out.println(String.format("Has next replied %d times", cnt));
                            }
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
