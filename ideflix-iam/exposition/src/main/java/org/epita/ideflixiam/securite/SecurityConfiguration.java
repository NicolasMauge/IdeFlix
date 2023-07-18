package org.epita.ideflixiam.securite;

import org.epita.ideflixiam.application.utilisateur.UtilisateurService;
import org.epita.ideflixiam.exposition.utilisateur.UtilisateurConvertisseur;
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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.epita.ideflixiam.application.common.UtileRole.ROLE_ADMIN;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    private final static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    private final static String[] SWAGGER_WHITELIST = {
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    @Value("${org.epita.ideflixiam.secretiam}")
    public String SECRET_IAM;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    UtilisateurConvertisseur utilisateurConvertisseur;
    @Autowired
    private DataSource dataSource;
    @Value("${server.servlet.context-path}")
    private String contextPath;


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.debug("IAM - Chemin par défaut des end-points : " + contextPath);

        final String roleAdmin = ROLE_ADMIN.substring("ROLE_".length());

        http
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/etat").permitAll()
                .antMatchers(HttpMethod.POST, "/utilisateur").permitAll()
                .antMatchers(SWAGGER_WHITELIST).hasRole(roleAdmin)
                .antMatchers(HttpMethod.GET, "/admin/utilisateurs").hasRole(roleAdmin)
//                .antMatchers(HttpMethod.GET, "/admin/utilisateurs").permitAll()
                .antMatchers(HttpMethod.DELETE, "/admin/utilisateurs/*").hasRole(roleAdmin)
                .antMatchers(HttpMethod.DELETE, "/admin/utilisateurs/**").hasRole(roleAdmin)
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JWTVerify(this.SECRET_IAM, utilisateurService), UsernamePasswordAuthenticationFilter.class)
                .addFilter(
                        new JWTAuthenticationManager(
                                authenticationManager(
                                        http.getSharedObject(
                                                AuthenticationConfiguration.class)),
                                this.SECRET_IAM,
                                utilisateurService,
                                utilisateurConvertisseur)
                )
                .csrf().disable()                  // TODO configure CSRF protection
                .formLogin().disable();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        logger.debug("IAM - configureGlobal : récupération des données d'authentification en base");

        final String requeteUtilisateur = "select email,mot_de_passe, is_actif from utilisateur_entity where email=? ";

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
