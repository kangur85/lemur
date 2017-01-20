package eu.kaszkowiak.poc.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by kan on 20.01.17.
 */
@Configuration
@Profile("spring-test")
public class LemurConfigTestFactory {

    @Bean
    @Primary
    public LemurConfig getConfig() {
        System.out.println("Test configuration fetched.");
        return new LemurConfig("./src/test/resources/exampleDirectory/");
    }
}
