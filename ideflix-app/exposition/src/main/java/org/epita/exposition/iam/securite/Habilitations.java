package org.epita.exposition.iam.securite;

import com.auth0.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class Habilitations {

    public static boolean isHabilitationCorrecte(String email) {
        if (email == null)
            return false;
        else {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();

            String emailConnecte = (String) authentication.getPrincipal();
            if (emailConnecte == null)
                return false;
            else
                return authentication.getPrincipal().equals(email);
        }
    }

}
