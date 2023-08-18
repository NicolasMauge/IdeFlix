package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieDataBaseException extends BusinessException{

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseException.class);

    public MovieDataBaseException(String message) {
        super(message);
        logger.error("APP - API MovieDataBase - " + message);

    }
}
