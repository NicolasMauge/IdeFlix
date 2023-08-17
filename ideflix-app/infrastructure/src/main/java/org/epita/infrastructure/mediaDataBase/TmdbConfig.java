package org.epita.infrastructure.mediaDataBase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TmdbConfig {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Bean
    public String getTmdbApiKey() {
//        System.out.println("key: " +  tmdbApiKey);
        return tmdbApiKey;
    }
}
