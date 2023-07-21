package org.epita.exposition.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReponseCommuneDto {

//    @Schema(name = "Statut HTTP", example = "200 OK")
//    private String httpStatus;

    @Schema(name = "Information complémentaire", example = "Objet créé")
    private String message;

    public ReponseCommuneDto(/*final String httpStatus,*/ final String message) {
//        this.httpStatus = httpStatus;
        this.message = message;
    }

}
