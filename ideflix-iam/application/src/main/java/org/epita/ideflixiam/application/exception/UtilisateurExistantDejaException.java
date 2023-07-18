package org.epita.ideflixiam.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilisateurExistantDejaException extends IdeFlixIamException {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurExistantDejaException.class);


    public UtilisateurExistantDejaException(String message) {
        super(message);
        logger.warn("IAM - L'utilisateur existe déjà : " + message);

    }

}
