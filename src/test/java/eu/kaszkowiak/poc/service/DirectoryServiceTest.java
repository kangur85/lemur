package eu.kaszkowiak.poc.service;

import eu.kaszkowiak.poc.config.LemurConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

/**
 * Created by kan on 17.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("spring-test")
public class DirectoryServiceTest {

    @Autowired
    LemurConfig lemurConfig;

    @Autowired
    private DirectoryService directoryService;


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