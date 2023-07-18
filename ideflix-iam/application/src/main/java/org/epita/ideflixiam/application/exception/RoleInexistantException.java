package org.epita.ideflixiam.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleInexistantException extends IdeFlixIamException {

    private static final Logger logger = LoggerFactory.getLogger(RoleInexistantException.class);

    public RoleInexistantException(String message) {
        super(message);
        logger.debug("IAM - Rôle inexistant : " + message);

    }

}
