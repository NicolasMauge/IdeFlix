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


@Configuration
public class SecurityConfiguration {

    private final static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Value("${server.servlet.context-path}")
    private String contextPath;


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
                .antMatchers(HttpMethod.GET, "/swagger-ui/").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/utilisateur").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/iam/utilisateur").permitAll()
                .anyRequest().denyAll()
                .and()
                .addFilter(
                        new JWTAuthenticationManager(
                                authenticationManager(
                                        http.getSharedObject(
                                                AuthenticationConfiguration.class)))
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

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select email,mot_de_passe from utilisateur_entity where email=? ")
                .authoritiesByUsernameQuery("select u.email, r.nom_role from utilisateur_entity u join utilisateur_entity_liste_role_entities j on u.email=j.utilisateur_entity_email join role_entity r on j.liste_role_entities_id=r.id where u.email=? ");

    }

}
