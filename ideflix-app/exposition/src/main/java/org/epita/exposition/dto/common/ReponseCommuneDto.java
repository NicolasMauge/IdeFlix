package org.epita.exposition.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReponseCommuneDto {

    @Schema(name = "Statut HTTP", example = "200 OK")
    private String httpStatus;

    @Schema(name = "Information complémentaire", example = "Opération effectuée.")
    private String message;

    public ReponseCommuneDto(String message, String httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ReponseCommuneDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }
}
