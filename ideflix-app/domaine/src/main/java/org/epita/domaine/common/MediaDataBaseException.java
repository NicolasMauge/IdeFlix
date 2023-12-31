package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaDataBaseException extends BusinessException{

    private static final Logger logger = LoggerFactory.getLogger(MediaDataBaseException.class);

    public MediaDataBaseException(String message) {
        super(message);
        logger.warn("APP - API MovieDataBase - Erreur lors de la communication avec l'API externe : " + message);
    }
}
