package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityNotFoundException extends BusinessException {

    private static final Logger logger = LoggerFactory.getLogger(EntityNotFoundException.class);

    public EntityNotFoundException(String message) {
        super(message);
        logger.warn("APP - Entité non trouvée : " + message);
    }
}
