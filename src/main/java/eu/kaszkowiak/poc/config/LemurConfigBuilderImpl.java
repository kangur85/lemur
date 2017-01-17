package eu.kaszkowiak.poc.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kan on 17.01.17.
 */
@Configuration
public class LemurConfigBuilderImpl implements LemurConfigBuilder {

    @Getter
    @Value("${watchDirectoryPath}")
    private String watchDirectoryPath;

    @Bean
    public LemurConfig getConfig() {
        return new LemurConfig(watchDirectoryPath);
    }

}
