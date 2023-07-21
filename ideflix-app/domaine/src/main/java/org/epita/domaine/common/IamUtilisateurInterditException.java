package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamUtilisateurInterditException extends BusinessException {
    private static final Logger LOGGER = LoggerFactory.getLogger(IamUtilisateurInterditException.class);

    public IamUtilisateurInterditException(String message) {
        super(message);

        LOGGER.warn("IdeFlix - Login ou mot de passe erroné. " + message);
    }
}
