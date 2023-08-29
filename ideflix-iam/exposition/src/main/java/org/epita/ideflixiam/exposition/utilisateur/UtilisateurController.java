package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.epita.ideflixiam.application.exception.IdeFlixIamException;
import org.epita.ideflixiam.application.exception.UtilisateurInexistantException;
import org.epita.ideflixiam.application.utilisateur.UtilisateurService;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.exceptions.MessageExceptionDto;
import org.epita.ideflixiam.securite.Dechiffreur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Value("${org.epita.ideflixiam.secretiam}")
    public String SECRET_IAM;


    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurConvertisseur utilisateurConvertisseur) {
        this.utilisateurService = utilisateurService;
        this.utilisateurConvertisseur = utilisateurConvertisseur;
    }


    /**
     * Cette méthode permet de s'enrôler comme utilisateur standard quand on n'est pas connecté.
     * (L'email ne doit pas être déjà associé à un autre compte).
     *
     * @return : UtilisateurSimpleDto
     */

    @Operation(summary = "Créer un utilisateur standard.", method = "creerUtilisateur", description = "Lors du premier appel, l'administrateur est créé selon les données fournies dans le fichier de configuration utilisé au démarrage d'IdeFlix-IAM.")
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès."),
            @ApiResponse(responseCode = "400", description = "Requête erronée.", content = {@Content(schema = @Schema(implementation = MessageExceptionDto.class))}),
            @ApiResponse(responseCode = "409", description = "Email déjà utilisé.", content = {@Content(schema = @Schema(implementation = MessageExceptionDto.class))})
    })
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    @PostMapping("/utilisateur")
    public ResponseEntity<UtilisateurSimpleDto> creerUtilisateur(@RequestBody UtilisateurEntreeDto utilisateurEntreeDto) throws IdeFlixIamException {

        logger.debug("IAM - Création de l'utilisateur " + utilisateurEntreeDto.getEmail());

        Dechiffreur dechiffreur = new Dechiffreur(this.SECRET_IAM);

        logger.trace("IAM - chiffré   : " + utilisateurEntreeDto.getMotDePasse());

        String motDePasseClair = dechiffreur.dechiffrer(utilisateurEntreeDto.getMotDePasse(), utilisateurEntreeDto.getEmail());
        logger.trace("IAM - déchiffré : " + motDePasseClair);

        utilisateurEntreeDto.setMotDePasse(motDePasseClair);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(utilisateurConvertisseur.convertirEntiteVersSimpleDto(
                        utilisateurService.creerUtilisateur(
                                utilisateurConvertisseur
                                        .convertirDetailDtoVersEntite(utilisateurEntreeDto,
                                                ROLE_UTILISATEUR))));

    }


    @GetMapping("/init")
    @Operation(summary = "Initialisation de l'IAM.", method = "initialiserIam", description = "Cette ressource doit être appelée par l'APP pour récupérer l'admin par défaut si ce n'est déjà fait.")
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'initialisation a réussi ou l'application est déjà initialisée."),
    })
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    public ResponseEntity<UtilisateurSimpleDto> initialiserIam() {
        logger.debug("IAM - Initialisation du compte d'administration fourni en paramètre lors du démarrage de l'application.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(utilisateurConvertisseur.convertirEntiteVersSimpleDto(
                        this.utilisateurService.verifieQueIamEstInitialisee(nomAdmin, prenomAdmin, emailAdmin, motDePasseAdmin)));


    }

    // endpoint fictif pour documenter correctement le login dans le swagger
    @PostMapping("/login")
    @Operation(summary = "Connexion", description = "Connexion de l'utilisateur.")
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur s'est connecté avec succès", content = @Content(schema = @Schema(implementation = UtilisateurReponseLoginDto.class))),
            @ApiResponse(responseCode = "403", description = "Email ou mot de passe erroné.", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    public UtilisateurReponseLoginDto fakeLogin(@RequestBody UtilisateurLoginDto utilisateurLoginDto) {
        throw new IllegalStateException("Ne pas appeler cette méthode qui est créée uniquement pour alimenter le swagger");
    }

    // ========================================== Administrateurs ===================================================


    @GetMapping("/admin/utilisateurs")
    @Operation(summary = "Récupérer la liste des utilisateurs", method = "getUtilisateurs", description = "Cette ressource permet à un administrateur de récupérer la liste des utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK, voici la liste."),
            @ApiResponse(responseCode = "403", description = "Requête interdite.")
    })
//    @ApiImplicitParam(name = "Authorization", value = "JWT", required = true, dataTypeClass = String.class, example = "Bearer efdmlkjoij651.rqrgq.fqfe6f5")
    @CrossOrigin(origins = ORIGINES_IDEFLIX_STRING)
    public List<UtilisateurDetailDto> getUtilisateurs() {

        logger.debug("IAM - Récupération de tous utilisateurs");

        return utilisateurService
                .recupererUtilisateurs()
                .stream()
                .map(utilisateur -> utilisateurConvertisseur.convertirEntiteVersDetailDto(utilisateur))
                .toList();

    }

    @Operation(summary = "Effacer un utilisateur", method = "delUtilisateur", description = "Cette ressource permet à un administrateur d'effacer un utilisateur en fournissant son email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK, utilisateur effacé."),
            @ApiResponse(responseCode = "400", description = "Requête erronée. L'email est-il correct ?"),
            @ApiResponse(responseCode = "403", description = "Requête interdite.")
    })
    @Parameters(value = {
            @Parameter(name = "Email", schema = @Schema(implementation = String.class), description = "Email of the user to be deleted.", example = "john.doe@example.org", required = true),
            @Parameter(name = "Authorization", description = "JWT", required = true, schema = @Schema(implementation = String.class), example = "Bearer efdmlkjoij651.rqrgq.fqfe6f5")
    })
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
