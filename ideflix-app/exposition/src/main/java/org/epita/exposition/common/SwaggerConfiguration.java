package org.epita.exposition.common;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

// Voir la documentation ici : http://springfox.github.io/springfox/docs/current/#quick-start-guides

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any() /*springBootActuatorJmxPaths()*/)
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .securitySchemes(singletonList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));

    }

    private ApiInfo apiInfo() {
        VendorExtension<String> extension1 = new StringVendorExtension("Membre de l'équipe", "Caroline");
        VendorExtension<String> extension2 = new StringVendorExtension("Un autre membre de l'équipe", "Nicolas");
        VendorExtension<String> extension3 = new StringVendorExtension("Voici encore un autre membre de l'équipe", "Stéphane");
        List<VendorExtension> vendorExtensionList = Arrays.asList(extension1, extension2, extension3);

        return new ApiInfoBuilder()
                .title("IdeFlix - APP")
                .description("Gérez la liste des films et séries que vous voulez voir.")
                .contact(new Contact("EPITA", "https://www.epita.fr", "contact@epita.fr"))
                .version("IDEFLIX-APP V1.0.0")
                .extensions(vendorExtensionList)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Mettre ci-dessous Bearer suivi du JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference("Mettre ci-dessous Bearer suivi du JWT", authorizationScopes));
    }

}


