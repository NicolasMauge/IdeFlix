package org.epita.exposition.iam.utilisateuriam;

import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.epita.application.iam.service.UtilisateurIamService;
import org.epita.exposition.iam.utilisateuriam.dto.*;
import org.epita.exposition.iam.utilisateuriam.mapper.UtilisateurIamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/iam")
public class UtilisateurIamController {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurIamController.class);

    UtilisateurIamService utilisateurIamService;
    UtilisateurIamMapper utilisateurIamMapper;

    public UtilisateurIamController(UtilisateurIamService utilisateurIamService,
                                    UtilisateurIamMapper utilisateurIamMapper) {
        this.utilisateurIamService = utilisateurIamService;
        this.utilisateurIamMapper = utilisateurIamMapper;
    }

    // ======================================== Créer un utilisateur =============================================
    @ApiOperation(value = "Créer un utilisateur standard.",
            notes = "Lors du premier appel, l'administrateur IAM est créé selon les données fournies dans le fichier de configuration utilisé au démarrage d'IdeFlix-IAM.",
            response = UtilisateurIamCreationReponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Non utilisé."),
            @ApiResponse(code = 201, message = "Utilisateur créé avec succès."),
            @ApiResponse(code = 400, message = "Requête erronée."),
            @ApiResponse(code = 409, message = "L'utilisateur existe déjà.")
    })
    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping("/utilisateur")
    public ResponseEntity<UtilisateurIamCreationReponseDto> creerUtilisateurIam(@Valid @RequestBody @Validated UtilisateurIamCreationDto utilisateurIamCreationDto) {

        logger.debug("IdeFlix - Création de l'utilisateur " + utilisateurIamCreationDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utilisateurIamMapper.mapEntityToCreationResponseDto(
                        utilisateurIamService.creerUtilisateurIam(
                                utilisateurIamMapper.mapCreationDtoToEntity(
                                        utilisateurIamCreationDto))));
    }


    // ======================================== Connexion d'un utilisateur =============================================
    @ApiOperation(value = "Se connecter",
            response = UtilisateurIamLoginReponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Connexion réussie."),
            @ApiResponse(code = 400, message = "Requête erronée."),
            @ApiResponse(code = 403, message = "Utilisateur ou mot de passe incorrect."),
            @ApiResponse(code = 503, message = "Un problème de communication avec l'IAM a eu lieu.")
    })
    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping("/login")
    ResponseEntity<UtilisateurIamLoginReponseDto> login(@Valid @RequestBody UtilisateurIamLoginDto utilisateurIamLoginDto) {

        logger.debug("IdeFlix - Connexion de l'utilisateur " + utilisateurIamLoginDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurIamMapper.mapEntityToLoginReponseDto(
                        utilisateurIamService.loginIam(
                                utilisateurIamMapper.mapLoginDtoToEntity(utilisateurIamLoginDto))
                ));
    }


    // ============================================== Administration ===================================================
    //@ApiOperation(value = "Consulter la liste des utilisateurs")
    @Operation(summary = "Consulter la liste des utilisateurs", security = @SecurityRequirement(name = "bearerAuth"))
    @CrossOrigin(origins = "http://locahost:4200")
    @GetMapping("/admin/utilisateur/all")
    //@SecurityDefinition(@ApiKeyAuthDefinition(key = "ApiKey", in = "header", name = "Authorization", description = "Saisir Bearer suivi du JWT. Par exemple : Bearer eyxx.eyxx.xA"))
    ResponseEntity<List<UtilisateurIamDto>> getAllUtilisateurs(
            @RequestHeader(value = "Authorization") String authorizationHeader

    ) {
        logger.debug("Ideflix - Liste des utilisateurs - En-tête Authorization : " + authorizationHeader);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurIamMapper.mapEntityListToUtilisateurIamDto(utilisateurIamService.getUtilisateursIam(authorizationHeader)));
    }

}
