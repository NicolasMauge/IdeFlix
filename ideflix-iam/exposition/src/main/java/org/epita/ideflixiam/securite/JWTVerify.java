package org.epita.ideflixiam.securite;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        // Bearer token -> Supprimer 'bearer'
        String token = header.replace("Bearer ", "");


        logger.debug("SECRET_IAM = " + this.SECRET_IAM);

        //---------Verify token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.SECRET_IAM)).build();
        //DecodedJWT decodedJWT = verifier.verify(token);
    }
}
