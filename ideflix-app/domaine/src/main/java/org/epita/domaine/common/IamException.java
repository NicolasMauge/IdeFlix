package org.epita.domaine.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamException extends BusinessException {

    private static final Logger logger = LoggerFactory.getLogger(IamException.class);

    public IamException(String message) {
        super(message);
        logger.error("APP - IAM - " + message);

    }


}
