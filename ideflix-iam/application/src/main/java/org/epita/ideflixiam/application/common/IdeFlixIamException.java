package org.epita.ideflixiam.application.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdeFlixIamException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(IdeFlixIamException.class);

    public IdeFlixIamException() {
        super();
    }

    public IdeFlixIamException(String message) {
        super(message);
        logger.error("IAM - " + message);
    }

    public IdeFlixIamException(String message, Throwable cause) {
        super(message, cause);
        logger.error("IAM - " + message);

    }

    public IdeFlixIamException(Throwable cause) {
        super(cause);
    }

    protected IdeFlixIamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        logger.error("IAM - " + message);

    }
}
