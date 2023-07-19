package org.epita.exposition.iam.utilisateuriam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.epita.application.iam.service.UtilisateurIamService;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamCreationDto;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamCreationReponseDto;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamLoginDto;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamLoginReponseDto;
import org.epita.exposition.iam.utilisateuriam.mapper.UtilisateurIamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/utilisateur-iam")
    public ResponseEntity<UtilisateurIamCreationReponseDto> creerUtilisateurIam(@RequestBody UtilisateurIamCreationDto utilisateurIamCreationDto) {
//        UtilisateurIamSimpleDto utilisateurIamSimpleDto = new UtilisateurIamSimpleDto(1L,"DUBOUCHON","Mocky","mock@bouchon.fr","2023-07-18");

        logger.debug("IdeFlix - Création de l'utilisateur " + utilisateurIamCreationDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utilisateurIamMapper.mapEntityToCreationResponseDto(
                        utilisateurIamService.creerUtilisateurIam(
                                utilisateurIamMapper.mapCreationDtoToEntity(
                                        utilisateurIamCreationDto))));
    }


    @ApiOperation(value = "Se connecter",
            notes = "",
            response = UtilisateurIamLoginReponseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Connexion réussie."),
            @ApiResponse(code = 400, message = "Requête erronée."),
            @ApiResponse(code = 403, message = "Utilisateur ou mot de passe incorrect.")
    })
    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping("/utilisateur-iam/login")
    ResponseEntity<UtilisateurIamLoginReponseDto> login(@RequestBody UtilisateurIamLoginDto utilisateurIamLoginDto) {

        logger.debug("IdeFlix - Connexion de l'utilisateur " + utilisateurIamLoginDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurIamMapper.mapEntityToLoginReponseDto(
                        utilisateurIamService.loginIam(
                                utilisateurIamMapper.mapLoginDtoToEntity(utilisateurIamLoginDto))
                ));
    }


}
