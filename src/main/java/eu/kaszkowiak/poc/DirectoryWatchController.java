package eu.kaszkowiak.poc;

import io.reactivex.Observable;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;


@RestController
public class DirectoryWatchController {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    public Observable<List<Path>> getAll() {
        return Observable.just(new LinkedList<Path>());
    }


}
