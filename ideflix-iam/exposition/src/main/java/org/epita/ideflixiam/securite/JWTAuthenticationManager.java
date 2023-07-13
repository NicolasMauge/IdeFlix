package org.epita.ideflixiam.securite;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.epita.ideflixiam.exposition.utilisateur.UtilisateurEntreeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationManager extends UsernamePasswordAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(JWTAuthenticationManager.class);
    private final AuthenticationManager authenticationManager;

    //@Value("${org.epita.ideflixiam.secretiam}")
    public String SECRET_IAM;

    public JWTAuthenticationManager(AuthenticationManager authenticationManager, String secretIam) {
        this.authenticationManager = authenticationManager;
        this.SECRET_IAM = secretIam;
    }


    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {


        ObjectMapper mapper = new ObjectMapper();
        UtilisateurEntreeDto utilisateurDto = null;

        try {
            //Convertir le contenu du body de la requête en JSON
            // vers un objet java en utilisant Jackson
            utilisateurDto = mapper
                    .readValue(request.getInputStream(), UtilisateurEntreeDto.class);

            logger.debug("IAM - attemptAuthentification : " + utilisateurDto.getEmail());

        } catch (IOException e) {
            logger.debug("IAM - Exception attemptAuthentification");
            throw new RuntimeException(e);
        }


        logger.debug("IAM - Authentification de " + utilisateurDto.getEmail());

        return authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                utilisateurDto.getEmail(),
                                utilisateurDto.getMotDePasse()));
    }

    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException,
            ServletException {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au -> {
            roles.add(au.getAuthority());
        });

        logger.debug("IAM - SECRET_IAM = " + this.SECRET_IAM);

        String jwt = JWT.create().withSubject(springUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .sign(Algorithm.HMAC256(this.SECRET_IAM));
        response.addHeader("Authorization", "Bearer " + jwt); // on passe par le body car on ne sait pas lire le header depuis Angular
        response.getWriter().println("{\"email\":\""
                + springUser.getUsername()
                + "\", "
                + "\"jwt\":\"" + jwt
                + "\" }");
    }


}
