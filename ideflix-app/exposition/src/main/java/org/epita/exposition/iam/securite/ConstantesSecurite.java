package org.epita.exposition.iam.securite;

public final class ConstantesSecurite {

    public final static String[] SWAGGER_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}
