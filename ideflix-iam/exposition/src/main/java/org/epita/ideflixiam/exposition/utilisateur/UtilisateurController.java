package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.annotations.*;
import org.epita.ideflixiam.application.exception.IdeFlixIamException;
import org.epita.ideflixiam.application.exception.UtilisateurInexistantException;
import org.epita.ideflixiam.application.utilisateur.UtilisateurService;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.epita.ideflixiam.application.common.UtileRole.ROLE_UTILISATEUR;
import static org.epita.ideflixiam.common.ConstantesUtiles.*;

@RestController
@RequestMapping
//@CrossOrigin(origins = "http://localhost:4200",
//        allowedHeaders = {"Authorization"},
//        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.DELETE})
public class UtilisateurController {

    Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    UtilisateurService utilisateurService;
    UtilisateurConvertisseur utilisateurConvertisseur;

    @Value("${org.epita.ideflixiam.admin.email}")
    private String emailAdmin;

    @Value("${org.epita.ideflixiam.admin.lastname}")
    private String nomAdmin;

    @Value("${org.epita.ideflixiam.admin.firstname}")
    private String prenomAdmin;

    @Value("${org.epita.ideflixiam.admin.password}")
    private String motDePasseAdmin;

    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurConvertisseur utilisateurConvertisseur) {
        this.utilisateurService = utilisateurService;
        this.utilisateurConvertisseur = utilisateurConvertisseur;
    }


    /**
     * Cette méthode permet de s'enrôler comme utilisateur standard quand on n'est pas connecté.
     * (L'email ne doit pas être déjà associé à un autre compte).
     *
     * @param : UtilisateurEntreeDto
     * @return : UtilisateurSimpleDto
     */

    @ApiOperation(value = "Créer un utilisateur standard.", nickname = "creerUtilisateur", notes = "Lors du premier appel, l'administrateur est créé selon les données fournies dans le fichier de configuration utilisé au démarrage d'IdeFlix-IAM.", response = UtilisateurSimpleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Non utilisé."),
            @ApiResponse(code = 201, message = "Utilisateur créé avec succès."),
            @ApiResponse(code = 400, message = "Requête erronée."),
            @ApiResponse(code = 409, message = "Email déjà utilisé.")
    })
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    @PostMapping("/utilisateur")
    public ResponseEntity<UtilisateurSimpleDto> creerUtilisateur(@RequestBody UtilisateurEntreeDto utilisateurEntreeDto) throws IdeFlixIamException {

        logger.debug("IAM - Création de l'utilisateur " + utilisateurEntreeDto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utilisateurConvertisseur.convertirEntiteVersSimpleDto(
                        utilisateurService.creerUtilisateur(
                                utilisateurConvertisseur
                                        .convertirDetailDtoVersEntite(utilisateurEntreeDto,
                                                ROLE_UTILISATEUR))));

    }


    @GetMapping("/init")
    @ApiOperation(value = "Initialisation de l'IAM.", nickname = "initIam", notes = "Cette ressource doit être appelée par l'APP pour récupérer l'admin par défaut si ce n'est déjà fait.", response = UtilisateurSimpleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'initialisation a réussi ou l'application est déjà initialisée."),
    })
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    public ResponseEntity<UtilisateurSimpleDto> initialiserIam() {
        logger.debug("IAM - Initialisation du compte d'administration fourni en paramètre lors du démarrage de l'application.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurConvertisseur.convertirEntiteVersSimpleDto(
                        this.utilisateurService.verifieQueIamEstInitialisee(nomAdmin, prenomAdmin, emailAdmin, motDePasseAdmin)));


    }


    // ========================================== Administrateurs ===================================================


    @ApiOperation(value = "Récupérer la liste des utilisateurs", nickname = "getUtilisateurs", notes = "Cette ressource permet à un administrateur de récupérer la liste des utilisateurs", response = UtilisateurDetailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK, voici la liste."),
            @ApiResponse(code = 403, message = "Requête interdite.")
    })
    @ApiImplicitParam(name = "Authorization", value = "JWT", required = true, dataTypeClass = String.class, example = "Bearer efdmlkjoij651.rqrgq.fqfe6f5")
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    @GetMapping("/admin/utilisateurs")
    public List<UtilisateurDetailDto> getUtilisateurs() {

        logger.debug("IAM - Récupération de tous utilisateurs");

        return utilisateurService
                .recupererUtilisateurs()
                .stream()
                .map(utilisateur -> utilisateurConvertisseur.convertirEntiteVersDetailDto(utilisateur))
                .toList();

    }

    @ApiOperation(value = "Effacer un utilisateur", nickname = "delUtilisateur", notes = "Cette ressource permet à un administrateur d'effacer un utilisateur en fournissant son email.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK, utilisateur effacé."),
            @ApiResponse(code = 400, message = "Requête erronée. L'email est-il correct ?"),
            @ApiResponse(code = 403, message = "Requête interdite.")
    })
    @ApiParam(name = "Email", type = "String", value = "Email of the user to be deleted.", allowableValues = "john.doe@example.org", required = true)
    @ApiImplicitParam(name = "Authorization", value = "JWT", required = true, dataTypeClass = String.class, example = "Bearer efdmlkjoij651.rqrgq.fqfe6f5")
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    @DeleteMapping("/admin/utilisateurs/{email}")
    public void delUtilisateur(@PathVariable("email") String email) throws UtilisateurInexistantException {
        logger.debug("IAM - Suppression de " + email);

        UtilisateurEntity utilisateur = utilisateurService.recupererUtilisateurParEmail(email);

        if (utilisateur == null) {
            throw new UtilisateurInexistantException("IAM - Echec suppression de " + email + ". L'utilisateur n'existe pas.");
        } else {
            utilisateurService.supprimerUtilisateur(utilisateur);
        }
    }


}
