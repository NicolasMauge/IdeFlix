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


    public final static String[] PATH_GET_ANONYME_WHITELIST = {
            "/genre"
    };


    public final static String[] PATH_POST_ANONYME_WHITELIST = {
            "/iam/login",
            "/iam/utilisateur"
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
            "/mediaselectionne/utilisateur/**",
//            "/mediaselectionne/utilisateur/*/idtmdb/*",
            "/preferences",
            "/preferences/*",
            "/preferences/health-check",
            "/preferences/utilisateur/*",
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
            "/utilisateur/health-check",
            "/MovieDataBase/rechercheFilm/*",
            "/MovieDataBase/rechercheSerie/*",
            "/MovieDataBase/detailFilm/*",
            "/MovieDataBase/detailSerie/*",
            "/MovieDataBase/suggestionsFilm/*"
    };

    public final static String[] PATH_POST_UTILISATEUR_WHITELIST = {
            "/etiquette",
            "/etiquette/utilisateur/*",
            "/film",
            "/film/*",
            "/filmselectionne",
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
            "/mediaselectionne",
            "/etiquette/utilisateur/*"
    };

    public final static String[] PATH_DELETE_UTILISATEUR_WHITELIST = {
            "/preferences/*",
            "/etiquette/*",
            "/filmselectionne/*",
            "/serieselectionnee/*",
            "/mediaselectionne/*/*",
            "/utilisateur/*"
    };

    public final static String[] PATH_GET_ADMINISTRATEUR_WHITELIST = {
            "/iam/admin/utilisateur/all"
    };

    public final static String[] PATH_POST_ADMINISTRATEUR_WHITELIST = {

    };

    public final static String[] PATH_DELETE_ADMINISTRATEUR_WHITELIST = {
            "/film/*",
            "/genre/*",
            "/serie/*",
            "/iam/admin/utilisateur/*"

    };
}
