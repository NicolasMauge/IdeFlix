package org.epita.ideflixiam.application.common;

public class RoleInexistantException extends IdeFlixIamException {

    public RoleInexistantException() {
        super();
    }

    public RoleInexistantException(String message) {
        super(message);
    }

    public RoleInexistantException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleInexistantException(Throwable cause) {
        super(cause);
    }

    protected RoleInexistantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
