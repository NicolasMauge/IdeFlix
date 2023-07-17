package org.epita.ideflixiam.exceptions;

import java.time.LocalDateTime;

public class MessageExceptionDto {
    private String code;
    private String message;

    private LocalDateTime dateTime;

    public MessageExceptionDto(String code, String message, LocalDateTime dateTime) {
        this.code = code;
        this.message = message;
        this.dateTime = dateTime;
    }

    public MessageExceptionDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
