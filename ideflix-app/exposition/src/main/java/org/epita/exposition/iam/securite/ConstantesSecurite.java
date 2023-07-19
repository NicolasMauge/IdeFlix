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


    public final static String[] PATH_POST_ANONYME_WHITELIST = {
            "/login",
            "/utilisateur-iam"
    };

    public final static String[] PATH_GET_UTILISATEUR_WHITELIST = {
            "/etiquette",
            "/etiquette/*",
            "/etiquette/health-check",
            "/etiquette/utilisateur/*",
            "/film",
            "/film/health-check",
            "/filmselectionne",
            "/filmselectionne/*",
            "/filmselectionne/health-check",
            "/filmselectionne/utilisateur/*",
            "/genre",
            "/genre/*",
            "/genre/health-check",
            "/mediaselectionne/utilisateur/*",
            "/preferences",
            "/preferences/*",
            "/preferences/health-check",
            "/serie",
            "/serie/*",
            "/serie/health-check",
            "/serieselectionnee",
            "/serieselectionnee/*",
            "/serieselectionnee/health-check",
            "/serieselectionnee/utilisateur/*",
            "/utilisateur",
            "/utilisateur/*",
            "/utilisateur/email/*",
            "/utilisateur/health-check"
    };

    public final static String[] PATH_POST_UTILISATEUR_WHITELIST = {
            "/etiquette",
            "/film",
            "/film/*",
            "/filmselectionne",
//            "/filmselectionne/*",
            "/genre",
            "/genre/masse",
            "/preferences",
            "/preferences/addgenre/*",
            "/preferences/addgenre/**",
            "/preferences/masse",
            "/serie",
            "/serieselectionnee",
            "/utilisateur",
            "/utilisateur/etpref/*",
            "/utilisateur/masse",
            "/mediaselectionne"
    };

    public final static String[] PATH_DELETE_UTILISATEUR_WHITELIST = {
            "/preferences/*",
            "/etiquette/*",
            "/filmselectionne/*",
            "/serieselectionnee/*",
            "/utilisateur/*"
    };

    public final static String[] PATH_GET_ADMINISTRATEUR_WHITELIST = {

    };

    public final static String[] PATH_POST_ADMINISTRATEUR_WHITELIST = {

    };

    public final static String[] PATH_DELETE_ADMINISTRATEUR_WHITELIST = {
            "/film/*",
            "/genre/*",
            "/serie/*"
    };
}
