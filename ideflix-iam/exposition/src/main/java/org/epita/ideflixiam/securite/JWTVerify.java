package org.epita.ideflixiam.securite;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTVerify extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JWTVerify.class);

    @Value("${org.epita.ideflixiam.secretiam}")
    public String SECRET_IAM;

    public JWTVerify() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        //Recuperer le token dans les headers avec la clé Authorization

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        //---------Verify token
        logger.debug("SECRET_IAM = " + this.SECRET_IAM);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.SECRET_IAM)).build();
        //DecodedJWT decodedJWT = verifier.verify(token);

        // Bearer token -> Supprimer 'Bearer ' pour obtenir le tocken à vérifier
        String token = bearerToken.replace("Bearer ", "");
        logger.debug("JWT = " + token);
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();

        // on récupère les rôles

        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (String r : roles)
            authorities.add(new SimpleGrantedAuthority(r));

        //Authentifie l'utilisateur
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(request, response);

    }
}
