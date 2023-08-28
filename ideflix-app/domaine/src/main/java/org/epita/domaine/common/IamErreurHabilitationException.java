package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamErreurHabilitationException extends BusinessException {

    private static final Logger LOGGER = LoggerFactory.getLogger(IamErreurHabilitationException.class);

    public IamErreurHabilitationException(String message) {
        super(message);

        LOGGER.warn("IdeFlix - Utilisateur non autorisé. " + message);
    }

}
