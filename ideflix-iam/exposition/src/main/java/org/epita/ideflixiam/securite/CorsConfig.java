package org.epita.ideflixiam.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.epita.ideflixiam.common.ConstantesUtiles.*;

@Configuration
public class CorsConfig {

    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(ORIGINES_IDEFLIX_ARRAY));
//        configuration.addAllowedOrigin(ORIGINE_IDEFLIX_IHM); // IdeFlix-IHM
//        configuration.addAllowedOrigin(ORIGINE_IDEFLIX_APP); // IdeFlix-APP
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Origin", "X-Requested-With", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);

    }


}
