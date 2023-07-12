package org.epita.ideflixiam.securite;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.epita.ideflixiam.common.ConstanteUtile.SECRET_IAM;

public class JWTVerify extends OncePerRequestFilter {
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

        //---------Verify token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_IAM)).build();
        //DecodedJWT decodedJWT = verifier.verify(token);
    }
}
