package org.epita.infrastructure.utilisateur.iam.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamUtilisateurExisteDejaException extends IamException {

    private static final Logger logger = LoggerFactory.getLogger(IamUtilisateurExisteDejaException.class);

    public IamUtilisateurExisteDejaException(String message) {
        super(message);
        logger.error("APP - IAM - L'utilisateur existe déjà. " + message);
    }
}
