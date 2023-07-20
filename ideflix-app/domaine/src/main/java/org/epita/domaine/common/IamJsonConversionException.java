package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamJsonConversionException extends BusinessException {
    private static final Logger logger = LoggerFactory.getLogger(IamJsonConversionException.class);

    public IamJsonConversionException(String message) {
        super(message);
        logger.error("APP - IAM : impossible de convertir le JSON reçu de l'IAM. " + message);
    }
}
