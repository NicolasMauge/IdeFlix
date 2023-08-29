package org.epita.exposition.iam.utilisateuriam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.iam.service.UtilisateurIamService;
import org.epita.exposition.common.ResponseEntityCommune;
import org.epita.exposition.dto.common.ReponseCommuneDto;
import org.epita.exposition.iam.securite.Chiffreur;
import org.epita.exposition.iam.securite.IdeFlixSecurityConfiguration;
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
    Chiffreur chiffreur;

    public UtilisateurIamController(UtilisateurIamService utilisateurIamService,
                                    UtilisateurIamMapper utilisateurIamMapper,
                                    Chiffreur chiffreur) {
        this.utilisateurIamService = utilisateurIamService;
        this.utilisateurIamMapper = utilisateurIamMapper;
        this.chiffreur = chiffreur;
    }

    // ======================================== Créer un utilisateur =============================================
    @Operation(summary = "Créer un utilisateur standard.", method = "creerUtilisateurIam",
            description = "Lors du premier appel, l'administrateur IAM est créé selon les données fournies dans le fichier de configuration utilisé au démarrage d'IdeFlix-IAM.")
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès."),
            @ApiResponse(responseCode = "400", description = "Requête erronée."),
            @ApiResponse(responseCode = "409", description = "L'utilisateur existe déjà.")
    })
    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping("/utilisateur")
    @Tag(name = "Utilisateur")
    public ResponseEntity<UtilisateurIamCreationReponseDto> creerUtilisateurIam(@Valid @RequestBody @Validated UtilisateurIamCreationDto utilisateurIamCreationDto) {

        logger.debug("IdeFlix - Création de l'utilisateur " + utilisateurIamCreationDto.getEmail());

        String mdpChiffre = chiffreur.chiffrer(utilisateurIamCreationDto.getMotDePasse(),
                utilisateurIamCreationDto.getEmail());

        utilisateurIamCreationDto.setMotDePasse(mdpChiffre);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utilisateurIamMapper.mapEntityToCreationResponseDto(
                        utilisateurIamService.creerUtilisateurIam(
                                utilisateurIamMapper.mapCreationDtoToEntity(
                                        utilisateurIamCreationDto))));
    }


    // ======================================== Connexion d'un utilisateur =============================================
    @PostMapping("/login")
    @Operation(summary = "Se connecter", method = "login")
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie."),
            @ApiResponse(responseCode = "400", description = "Requête erronée."),
            @ApiResponse(responseCode = "403", description = "Utilisateur ou mot de passe incorrect."),
            @ApiResponse(responseCode = "503", description = "Un problème de communication avec l'IAM a eu lieu.")
    })
    @CrossOrigin(origins = "http://locahost:4200")
    @Tag(name = "Utilisateur")
    ResponseEntity<UtilisateurIamLoginReponseDto> login(@Valid @RequestBody UtilisateurIamLoginDto utilisateurIamLoginDto) {

        logger.debug("IdeFlix - Connexion de l'utilisateur " + utilisateurIamLoginDto.getEmail());

        logger.debug(utilisateurIamLoginDto.getMotDePasse());
        String mdpChiffre = chiffreur.chiffrer(utilisateurIamLoginDto.getMotDePasse(),
                utilisateurIamLoginDto.getEmail());

        utilisateurIamLoginDto.setMotDePasse(mdpChiffre);
        logger.debug(mdpChiffre);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurIamMapper.mapEntityToLoginReponseDto(
                        utilisateurIamService.loginIam(
                                utilisateurIamMapper.mapLoginDtoToEntity(utilisateurIamLoginDto))
                ));
    }

    // ======================================== Déconnexion d'un utilisateur ===========================================
    @Operation(summary = "Se déconnecter.", method = "logout")
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Déconnexion réussie.")
    }
    )
    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping("/logout")
    @Tag(name = "Utilisateur")
    ResponseEntity<ReponseCommuneDto> logout(@RequestBody String chaineVide) {
        logger.debug("IdeFlix - Déconnexion");
        return ResponseEntityCommune.get("Déconnexion IdeFlix", HttpStatus.OK);
    }


    // ============================================== Administration ===================================================
    @Operation(summary = "Consulter la liste des utilisateurs.", method = "getAllUtilisateurs",
            description = "Cette ressource permet à un administrateur de récupérer la liste des utilisateurs.")
    @CrossOrigin(origins = "http://locahost:4200")
    @GetMapping("/admin/utilisateur/all")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs fournies."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé.")
    })
    @Tag(name = "Utilisateur - Administration")
    ResponseEntity<List<UtilisateurIamDto>> getAllUtilisateurs(
            @RequestHeader(value = "Authorization") String authorizationHeader

    ) {
        logger.debug("Ideflix - Liste des utilisateurs - En-tête Authorization : " + authorizationHeader);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurIamMapper.mapEntityListToUtilisateurIamDto(utilisateurIamService.getUtilisateursIam(authorizationHeader)));
    }

    @DeleteMapping("/admin/utilisateur/{email}")
    @Operation(summary = "Supprimer un utilisateur.", method = "deleteUtilisateur",
            description = "Cette ressource permet à un administrateur de supprimer un utilisateur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Effacement réussi."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé.")
    })
    @Tag(name = "Utilisateur - Administration")
    ResponseEntity<ReponseCommuneDto> deleteUtilisateur(
            @RequestHeader(value = "Authorization") String authorizationHeader,
            @PathVariable("email") String email
    ) {
        logger.debug("Ideflix - Effacer " + email + ". En-tête Authorization : " + authorizationHeader);

        utilisateurIamService.delUtilisateurIam(authorizationHeader, email);
        return ResponseEntityCommune.get("IdeFlix - Utilisateur " + email + " effacé.", HttpStatus.OK);


    }

}
