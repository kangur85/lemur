package eu.kaszkowiak.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by kan on 17.01.17.
 */
@Profile("test")
@Configuration
public class LemurTestConfigBuilder implements LemurConfigBuilder {

    @Bean
    @Primary
    public LemurConfig getConfig() {
        return new LemurConfig("./src/test/resources/exampleDirectory/");
    }
}