package eu.kaszkowiak.poc.config.impl;

import eu.kaszkowiak.poc.config.LemurConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by kan on 20.01.17.
 */
@Configuration
@Profile("test")
public class LemurConfigTestFactory {

    @Bean
    @Primary
    public LemurConfig getConfig() {
        return new LemurConfig("./src/test/resources/exampleDirectory/");
    }
}
