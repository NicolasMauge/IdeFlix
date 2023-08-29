package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaDataBaseUnAuthorizedAccessException extends BusinessException{

    private static final Logger logger = LoggerFactory.getLogger(MediaDataBaseUnAuthorizedAccessException.class);

    public MediaDataBaseUnAuthorizedAccessException(String message) {
        super(message);
        logger.warn("APP - API MovieDataBase - Accès non autorisé à l'API  : " + message);
    }
}
