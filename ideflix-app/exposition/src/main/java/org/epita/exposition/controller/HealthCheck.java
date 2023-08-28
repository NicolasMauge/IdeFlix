package org.epita.exposition.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.exposition.common.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
@Tag(name = "Health Check")
public class HealthCheck {

    @Operation(summary = "Vérifie l'état de l'application",
            description = "Retour OK si l'application est en service.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK : étiquette créée pour l'utilisateur.")
    })
    @SecurityRequirements(value = {})
    @GetMapping
    public ResponseEntity<String> getHealthCheck() {
        return new ResponseEntity<>("IdeFix APP est en service.", HttpStatus.OK);
    }


}
