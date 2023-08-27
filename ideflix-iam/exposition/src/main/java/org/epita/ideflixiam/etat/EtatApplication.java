package org.epita.ideflixiam.etat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class EtatApplication {

    @GetMapping
    @Operation(summary = "Health-check",
            method = "getEtatApplication",
            description = "Simple endpoint permettant de vérifier si l'application est accessible et en fonctionnement. Si tout va bien, la réponse est \"IAM - IdeFlix IAM est en service\".")
    @SecurityRequirements(value = {})
    String getEtatApplication() {
        return "IAM - IdeFlix IAM est en service. ";
    }


}
