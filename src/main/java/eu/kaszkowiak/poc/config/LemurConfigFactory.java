package eu.kaszkowiak.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kan on 17.01.17.
 */
@Configuration
public interface LemurConfigFactory {

    @Bean
    LemurConfig getConfig();
}
