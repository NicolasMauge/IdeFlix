package org.epita.infrastructure.utilisateur.iam.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IamException extends RuntimeException {

    public IamException(String message) {
        super(message);
    }


}
