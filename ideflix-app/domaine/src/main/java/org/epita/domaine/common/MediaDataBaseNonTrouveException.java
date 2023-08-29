package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaDataBaseNonTrouveException extends BusinessException{

    private static final Logger logger = LoggerFactory.getLogger(MediaDataBaseNonTrouveException.class);

    public MediaDataBaseNonTrouveException(String message) {
        super(message);
        logger.warn("APP - API MovieDataBase - Media non trouvé : " + message);
    }
}
