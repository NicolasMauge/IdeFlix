package org.epita.exposition.iam.utilisateuriam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.epita.application.iam.service.UtilisateurIamService;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamLoginDto;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamLoginReponseDto;
import org.epita.exposition.iam.utilisateuriam.mapper.UtilisateurIamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    UtilisateurIamService utilisateurIamService;
    UtilisateurIamMapper utilisateurIamMapper;

    public LoginController(UtilisateurIamService utilisateurIamService,
                           UtilisateurIamMapper utilisateurIamMapper) {
        this.utilisateurIamService = utilisateurIamService;
        this.utilisateurIamMapper = utilisateurIamMapper;
    }


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
}
