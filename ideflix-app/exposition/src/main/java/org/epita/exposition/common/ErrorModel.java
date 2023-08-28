package org.epita.exposition.common;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorModel {
    @Schema(description = "Code HTTP de la réponse", example = "200 OK")
    private String code;
    @Schema(description = "Message d'erreur.")
    private String message;
    @Schema(description = "Complément d'information.")
    private String description;

    public ErrorModel(String code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public ErrorModel() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
