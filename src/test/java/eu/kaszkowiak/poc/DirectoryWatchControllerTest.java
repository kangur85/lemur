package eu.kaszkowiak.poc;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DirectoryWatchControllerTest {

    @Autowired
    private DirectoryWatchController watchController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldGetFileList() {
        Observable<FileEntry> fileEntries = watchController.getAll();
        TestObserver<FileEntry> testObserver = new TestObserver<>();
        fileEntries.subscribe(testObserver);
        testObserver.assertNoErrors();
//        testObserver.assertNotComplete();
    }

    @Test
    public void shouldGetPathFromProperties() {
        assertThat(watchController.getWatchDirectoryPath()).isNotNull();
    }

}
