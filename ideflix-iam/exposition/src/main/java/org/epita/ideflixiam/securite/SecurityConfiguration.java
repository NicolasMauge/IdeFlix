package org.epita.ideflixiam.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.epita.ideflixiam.application.common.Util.ROLE_ADMIN;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
//                .httpBasic()    // voir si on l'enlève (c'est pour login/mdp basic)
//                .and()
                .authorizeHttpRequests()
//                .antMatchers("/api/v1/iam/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/iam/utilisateur-iam").permitAll()
                .antMatchers(HttpMethod.POST, "/utilisateur-iam").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/iam/admin").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET).permitAll()
                //.antMatchers(HttpMethod.GET).hasRole("USER")
                //.anyRequest().denyAll()
                .and()
                //.addFilterBefore(new JWTVerify(), UsernamePasswordAuthenticationFilter.class)
                //.csrf().disable()
                .formLogin().disable();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select email,mot_de_passe from utilisateur_iam where email=? ")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=? ");

    }

}
