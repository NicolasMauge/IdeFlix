package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotFoundInJson extends BusinessException {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotFoundInJson.class);

    public EmailNotFoundInJson(String message) {
        super(message);
        logger.warn("APP - Email non trouvé : " + message);

    }
}
