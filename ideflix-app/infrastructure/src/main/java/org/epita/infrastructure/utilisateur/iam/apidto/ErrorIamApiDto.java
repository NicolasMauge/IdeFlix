package org.epita.infrastructure.utilisateur.iam.apidto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ErrorIamApiDto {
    private String code;
    private String message;
    private String dateTime;

    public ErrorIamApiDto(String code, String message, String dateTime) {
        this.code = code;
        this.message = message;
        this.dateTime = dateTime;
    }

    public ErrorIamApiDto() {
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
