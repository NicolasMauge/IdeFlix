package org.epita.ideflixiam.application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErreurFormatLoginException extends IdeFlixIamException {

    private static final Logger logger = LoggerFactory.getLogger(ErreurFormatLoginException.class);

    public ErreurFormatLoginException(String message) {
        super(message);
        logger.debug("IAM - login : erreur de format : " + message);
    }

}
