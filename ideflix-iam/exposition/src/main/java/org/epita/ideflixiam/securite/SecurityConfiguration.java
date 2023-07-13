package org.epita.ideflixiam.securite;

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

import javax.sql.DataSource;

import static org.epita.ideflixiam.application.common.Util.ROLE_ADMIN;


@Configuration
public class SecurityConfiguration {


    private final static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    @Value("${org.epita.ideflixiam.secretiam}")
    public String SECRET_IAM;
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    private static final String[] SWAGGER_WHITELIST = {
            // -- Swagger UI v2
//            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        if (this.passwordEncoder == null) {
            this.passwordEncoder = new BCryptPasswordEncoder();
        }
        return this.passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.debug("IAM, chemin par défaut des end-points : " + contextPath);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, SWAGGER_WHITELIST).permitAll()
//                .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/utilisateur").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/iam/utilisateur").permitAll()
                .antMatchers(SWAGGER_WHITELIST).hasRole(ROLE_ADMIN.substring("ROLE_".length()))
                .anyRequest().denyAll() // commenter pour que le swagger soit accessible
                .and()
                .addFilter(
                        new JWTAuthenticationManager(
                                authenticationManager(
                                        http.getSharedObject(
                                                AuthenticationConfiguration.class)), this.SECRET_IAM
                        )
                )
                //.antMatchers(HttpMethod.POST, "/utilisateur").permitAll()
//                .antMatchers(HttpMethod.POST, "/admin/utilisateurs").hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.GET).permitAll()
                //.antMatchers(HttpMethod.GET).hasRole("USER")
                //.anyRequest().denyAll()
//                .and()
                //.addFilterBefore(new JWTVerify(), UsernamePasswordAuthenticationFilter.class)
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
