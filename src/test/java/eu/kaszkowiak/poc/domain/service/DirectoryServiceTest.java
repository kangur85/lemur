package eu.kaszkowiak.poc.domain.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

/**
 * Created by kan on 17.01.17.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectoryServiceTest {

    @Autowired
    DirectoryService directoryService;

    @Test
    public void getDirectoryContents() throws Exception {


        StepVerifier.create(directoryService.getDirectoryContents())
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void getDirectoryChanges() throws Exception {

    }

}