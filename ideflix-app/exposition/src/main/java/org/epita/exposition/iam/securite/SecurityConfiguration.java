package org.epita.exposition.iam.securite;

import org.epita.application.iam.service.UtilisateurIamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.epita.application.iam.common.ConstantesRole.ROLE_ADMIN;
import static org.epita.application.iam.common.ConstantesRole.ROLE_UTILISATEUR;
import static org.epita.exposition.iam.securite.ConstantesSecurite.*;

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Value("${org.epita.ideflixapp.secretiam}")
    public String SECRET_IAM;
    //public String SECRET_IAM = "1234567890IAM";

    private UtilisateurIamService utilisateurIamService;

    private DataSource dataSource;

    public SecurityConfiguration(UtilisateurIamService utilisateurIamService, DataSource dataSource) {
        this.utilisateurIamService = utilisateurIamService;
        this.dataSource = dataSource;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final String roleAdmin = ROLE_ADMIN.substring("ROLE_".length());
        final String roleUtilisateur = ROLE_UTILISATEUR.substring("ROLE_".length());

        http
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, PATH_POST_ANONYME_WHITELIST).permitAll() // autorisé sans authentification
                .antMatchers(HttpMethod.GET, PATH_GET_ANONYME_WHITELIST).permitAll()   // autorisé sans authentification
                .antMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
                .antMatchers(SWAGGER_WHITELIST).hasRole(roleAdmin)  // avec rôle admin uniquement
                .antMatchers(HttpMethod.GET, PATH_GET_UTILISATEUR_WHITELIST).hasRole(roleUtilisateur) // avec rôle utilisateur
                .antMatchers(HttpMethod.POST, PATH_POST_UTILISATEUR_WHITELIST).hasRole(roleUtilisateur)
                .antMatchers(HttpMethod.DELETE, PATH_DELETE_UTILISATEUR_WHITELIST).hasRole(roleUtilisateur)
                .antMatchers(HttpMethod.GET, PATH_GET_ADMINISTRATEUR_WHITELIST).hasRole(roleAdmin)
                .antMatchers(HttpMethod.POST, PATH_POST_ADMINISTRATEUR_WHITELIST).hasRole(roleAdmin)
                .antMatchers(HttpMethod.DELETE, PATH_DELETE_ADMINISTRATEUR_WHITELIST).hasRole(roleAdmin)
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JWTVerify(this.SECRET_IAM
                                , utilisateurIamService)
                        , UsernamePasswordAuthenticationFilter.class)
//                .addFilter(
//                        new JWTAuthenticationManager(
//                                authenticationManager(
//                                        http.getSharedObject(
//                                                AuthenticationConfiguration.class)),
//                                this.SECRET_IAM,
//                                utilisateurService,
//                                utilisateurConvertisseur)  // La génération du JWT sera gérée par l'IAM
//                )
                .csrf().disable()                  // TODO configure CSRF protection
                .formLogin().disable();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        logger.debug("APP - configureGlobal : récupération des données d'authentification en base");


        // TODO fusion Utilisateur UtilisateurIam ?
        final String requeteUtilisateur = "select email,mot_de_passe, is_actif from utilisateur_entity where email=? ";

        // TODO fusion Utilisateur UtilisateurIam ?
        final String requeteRoles = "SELECT u.email, r.nom_role FROM utilisateur_entity u " +
                "JOIN utilisateur_entity_liste_role_entities ur ON u.id = ur.utilisateur_entity_id " +
                "JOIN role_entity r ON ur.liste_role_entities_id = r.id " +
                "WHERE u.email = ? ";


        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(requeteUtilisateur)
                .authoritiesByUsernameQuery(requeteRoles);

    }

}
