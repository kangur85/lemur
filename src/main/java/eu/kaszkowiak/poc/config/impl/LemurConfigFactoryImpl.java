package eu.kaszkowiak.poc.config.impl;

import eu.kaszkowiak.poc.config.LemurConfig;
import eu.kaszkowiak.poc.config.LemurConfigFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by kan on 17.01.17.
 */
@Configuration
@Profile("production")
public class LemurConfigFactoryImpl implements LemurConfigFactory {

    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    @Bean
    public LemurConfig getConfig() {
        return new LemurConfig(watchDirectoryPath);
    }

}
