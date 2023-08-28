package org.epita.exposition.controller.selection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.selection.etiquette.EtiquetteService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.common.IamErreurHabilitationException;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.ErrorModel;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.iam.securite.Habilitations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etiquette")
@Tag(name = "Sélection / Etiquette")
public class EtiquetteController {
    private EtiquetteService etiquetteService;
    private UtilisateurService utilisateurService;
    private Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;

    public EtiquetteController(EtiquetteService etiquetteService, UtilisateurService utilisateurService, Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper) {
        this.etiquetteService = etiquetteService;
        this.utilisateurService = utilisateurService;
        this.etiquetteMapper = etiquetteMapper;
    }

/*
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
*/

/*
    @PostMapping
    public ResponseEntity<String> creerEtiquette(@RequestBody EtiquetteDto etiquetteDto) {
        this.etiquetteService
                .creerEtiquette(
                        etiquetteMapper.mapDtoToEntity(etiquetteDto));

        return new ResponseEntity<String>("Etiquette créée", HttpStatus.CREATED);
    }
*/

    @Operation(summary = "Création d'une étiquette pour l'utilisateur",
            method = "creerEtiquetteParEmail",
            description = "L'étiquette a été ajoutée dans la liste des étiquettes de l'utilisateur.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK : étiquette créée pour l'utilisateur."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email fourni.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    @PostMapping("/utilisateur/{email}")
    public ResponseEntity<String> creerEtiquetteParEmail(
            @PathVariable("email") String email,
            @RequestBody EtiquetteDto etiquetteDto) throws IamErreurHabilitationException {
        if (Habilitations.isHabilitationCorrecte(email)) {
            UtilisateurEntity utilisateur = utilisateurService.trouverUtilisateurParEmail(email);

            EtiquetteEntity etiquette = this.etiquetteMapper.mapDtoToEntity(etiquetteDto);
            etiquette.setUtilisateurEntity(utilisateur);

            this.etiquetteService.creerEtiquette(etiquette);

            return new ResponseEntity<String>("Etiquette créée", HttpStatus.CREATED);
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }

/*
    @GetMapping("/{id}")
    public EtiquetteDto trouverEtiquetteParId(@PathVariable("id") Long id) {
        return this.etiquetteMapper
                .mapEntityToDto(
                        this.etiquetteService.trouverEtiquetteParId(id));
    }
*/

/*
    @GetMapping
    public List<EtiquetteDto> trouverToutesLesEtiquettes() {
        return this.etiquetteMapper
                .mapListEntityToDto(
                        this.etiquetteService.trouverToutesLesEtiquettes());
    }
*/

/*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEtiquetteParId(@PathVariable("id") Long id) {
        this.etiquetteService.supprimerEtiquetteParId(id);

        return new ResponseEntity<String>("Etiquette supprimée", HttpStatus.OK);
    }
*/

    @Operation(summary = "Récupération des étiquettes de l'utilisateur",
            method = "creerEtiquetteParEmail",
            description = "La liste des étiquettes de l'utilisateur est fournie par ce endpoint.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK : étiquette créée pour l'utilisateur."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email fourni.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    @GetMapping("/utilisateur/{email}")
    public List<EtiquetteDto> trouverEtiquetteParEmailUtilisateur(
            @PathVariable("email") String email) throws IamErreurHabilitationException {

        if (Habilitations.isHabilitationCorrecte(email)) {
            return this.etiquetteMapper
                    .mapListEntityToDto(
                            this.etiquetteService
                                    .trouverEtiquettesParEmailUtilisateur(email));
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }
}

