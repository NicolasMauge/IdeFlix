package org.epita.exposition.controller.utilisateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.epita.application.media.genre.GenreService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.common.IamErreurHabilitationException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.ErrorModel;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.common.ResponseEntityCommune;
import org.epita.exposition.dto.common.ReponseCommuneDto;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.epita.exposition.iam.securite.Habilitations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;


@RestController
@RequestMapping("/preferences")
public class PreferencesUtilisateurController {
    static final Logger logger = LoggerFactory.getLogger(PreferencesUtilisateurController.class);

    private PreferencesUtilisateurService preferencesUtilisateurService;
    private GenreService genreService;
    private Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper;
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public PreferencesUtilisateurController(PreferencesUtilisateurService preferencesUtilisateurService, GenreService genreService, Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper, Mapper<GenreEntity, GenreDto> genreMapper) {
        this.preferencesUtilisateurService = preferencesUtilisateurService;
        this.genreService = genreService;
        this.preferencesUtilisateurMapper = preferencesUtilisateurMapper;
        this.genreMapper = genreMapper;
    }

/*
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
*/

    @PostMapping(value = "", produces = {"application/json"}, consumes = {"application/json"})
    @Operation(summary = "Créer les préférences d'un utilisateur",
            description = "Permet de stocker le pseudo et les genres préférés de l'utilisateur.")
    public ResponseEntity<ReponseCommuneDto> creerPreferencesUtilisateur(@RequestBody PreferencesUtilisateurDto preferencesUtilisateurDto) {
        this.preferencesUtilisateurService
                .creerPreferencesUtilisateur(
                        this.preferencesUtilisateurMapper.mapDtoToEntity(preferencesUtilisateurDto));

        return ResponseEntityCommune.get("Préférences utilisateur créées", HttpStatus.CREATED);
    }

/*
    @PostMapping("/masse")
    public ResponseEntity<String> creerPlusieursPreferences(@RequestBody List<PreferencesUtilisateurDto> preferencesUtilisateurDtoList) {
        preferencesUtilisateurDtoList
                .stream()
                .forEach(p -> this.preferencesUtilisateurService
                        .creerPreferencesUtilisateur(
                                this.preferencesUtilisateurMapper
                                        .mapDtoToEntity(p)));

        return new ResponseEntity<String>("Preferences utilisateurs créées", HttpStatus.CREATED);
    }
*/

/*
    @GetMapping("/{id}")
    public PreferencesUtilisateurDto trouverPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        return this.preferencesUtilisateurMapper
                .mapEntityToDto(
                        this.preferencesUtilisateurService.trouverPreferencesUtilisateurParId(id));
    }
*/

/*
    @GetMapping
    public List<PreferencesUtilisateurDto> trouverToutesLesPreferencesUtilisateurs() {
        return this.preferencesUtilisateurMapper
                .mapListEntityToDto(
                        this.preferencesUtilisateurService.trouverToutesLesPreferencesUtilisateurs());
    }
*/

/*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        this.preferencesUtilisateurService.supprimerPreferencesUtilisateurParId(id);

        return new ResponseEntity<String>("Preference utilisateur supprimée", HttpStatus.OK);
    }
*/

    @Operation(summary = "Récupération des préférences d'un utilisateur.",
            method = "trouverPreferencesUtilisateurParEmailUtilisateur",
            description = "Seul l'utilisateur lui-même est autorisé à récupérer ses préférences.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email demandé.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    @GetMapping("/utilisateur/{email}")
    public PreferencesUtilisateurDto trouverPreferencesUtilisateurParEmailUtilisateur(@Email @PathVariable("email") String email) throws IamErreurHabilitationException {

        boolean habilitationCorrecte = Habilitations.isHabilitationCorrecte(email);

        if (habilitationCorrecte)
            return this.preferencesUtilisateurMapper
                    .mapEntityToDto(
                            this.preferencesUtilisateurService
                                    .trouverPreferenceUtilisateurParEmailUtilisateur(email));

        else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }
}
