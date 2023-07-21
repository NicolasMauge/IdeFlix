package org.epita.exposition.common;

import org.epita.exposition.dto.common.ReponseCommuneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityCommune {

    public static ResponseEntity<ReponseCommuneDto> get(String message, HttpStatus httpStatus) {

        ReponseCommuneDto reponseCommuneDto = new ReponseCommuneDto(message);

        return new ResponseEntity<>(reponseCommuneDto, httpStatus);
    }

}
