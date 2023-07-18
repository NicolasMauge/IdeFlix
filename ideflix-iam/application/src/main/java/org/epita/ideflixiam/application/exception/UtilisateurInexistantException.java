package org.epita.ideflixiam.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilisateurInexistantException extends IdeFlixIamException {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurInexistantException.class);

    public UtilisateurInexistantException(String message) {
        super(message);
        logger.warn("IAM - L'utilisateur existe déjà : " + message);
    }

}
