package org.epita.ideflixiam.application.common;

public class IdeFlixIamException extends RuntimeException {

    public IdeFlixIamException() {
        super();
    }

    public IdeFlixIamException(String message) {
        super(message);
    }

    public IdeFlixIamException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdeFlixIamException(Throwable cause) {
        super(cause);
    }

    protected IdeFlixIamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
