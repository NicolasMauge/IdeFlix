package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamUtilisateurExisteDejaException extends BusinessException {

    private static final Logger logger = LoggerFactory.getLogger(IamUtilisateurExisteDejaException.class);

    public IamUtilisateurExisteDejaException(String message) {
        super(message);
        logger.warn("APP - IAM - L'utilisateur existe déjà. " + message);
    }
}
