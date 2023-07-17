package org.epita.ideflixiam.application.common;

public class UtilisateurExistantDejaException extends IdeFlixIamException {
    public UtilisateurExistantDejaException() {
        super();
    }

    public UtilisateurExistantDejaException(String message) {
        super(message);
    }

    public UtilisateurExistantDejaException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilisateurExistantDejaException(Throwable cause) {
        super(cause);
    }

    protected UtilisateurExistantDejaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
