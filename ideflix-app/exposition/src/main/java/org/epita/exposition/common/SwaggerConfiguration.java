package org.epita.exposition.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Voir la documentation springdoc ici : https://springdoc.org/migrating-from-springfox.html

@Configuration
public class SwaggerConfiguration {


    @Bean
    public OpenAPI ideflixIamOpenAPI() {

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme())
                )
                .info(new Info().title("IdeFlix - APP")
                        .description("Gérez la liste des films et séries que vous voulez voir.")
                        .version("IDEFLIX-APP v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("EPITA")
                                .url("https://www.epita.fr")
                                .email("contact@epita.fr")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation d'IdeFlix")
                        .url("https://epita-stephaneeveille.atlassian.net/wiki/spaces/DL/overview?homepageId=65734")
                );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

}


