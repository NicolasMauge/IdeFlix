package org.epita.exposition.iam.utilisateuriam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.epita.application.iam.service.UtilisateurIamService;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamSimpleDto;
import org.epita.exposition.iam.utilisateuriam.mapper.UtilisateurIamSimpleMapper;
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
    UtilisateurIamSimpleMapper utilisateurIamSimpleMapper;

    public UtilisateurIamController(UtilisateurIamService utilisateurIamService) {
        this.utilisateurIamService = utilisateurIamService;
    }

    @ApiOperation(value = "Créer un utilisateur standard.",
            notes = "Lors du premier appel, l'administrateur IAM est créé selon les données fournies dans le fichier de configuration utilisé au démarrage d'IdeFlix-IAM.",
            response = UtilisateurIamSimpleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Non utilisé."),
            @ApiResponse(code = 201, message = "Utilisateur créé avec succès."),
            @ApiResponse(code = 400, message = "Requête erronée."),
            @ApiResponse(code = 409, message = "L'utilisateur existe déjà.")
    })
    @CrossOrigin(origins = "http://locahost:4200")
    @PostMapping("/utilisateur")
    public ResponseEntity<UtilisateurIamSimpleDto> creerUtilisateurIam(@RequestBody UtilisateurIamSimpleDto utilisateurIamSimpleDto) {

//        UtilisateurIamSimpleDto utilisateurIamSimpleDto = new UtilisateurIamSimpleDto(
//                1L,
//                "DUBOUCHON",
//                "Mocky",
//                "mock@bouchon.fr",
//                "2023-07-18");

        logger.debug("Création de l'utilisateur " + utilisateurIamSimpleDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utilisateurIamSimpleMapper.mapEntityToDto(
                        utilisateurIamService.creerUtilisateurIam(
                                utilisateurIamSimpleMapper.mapDtoToEntity(
                                        utilisateurIamSimpleDto))));
    }
}
