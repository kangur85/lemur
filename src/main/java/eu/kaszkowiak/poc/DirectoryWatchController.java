package eu.kaszkowiak.poc;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@RestController
public class DirectoryWatchController {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    public List<Path> getAll() {
        return null;
    }


}
