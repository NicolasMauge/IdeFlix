package org.epita.exposition.controller.selection;

import org.epita.application.selection.etiquette.EtiquetteService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etiquette")
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

    @PostMapping("/utilisateur/{email}")
    public ResponseEntity<String> creerEtiquetteParEmail(@PathVariable("email") String email, @RequestBody EtiquetteDto etiquetteDto) {
        UtilisateurEntity utilisateur = utilisateurService.trouverUtilisateurParEmail(email);

        EtiquetteEntity etiquette = this.etiquetteMapper.mapDtoToEntity(etiquetteDto);
        etiquette.setUtilisateurEntity(utilisateur);

        this.etiquetteService.creerEtiquette(etiquette);

        return new ResponseEntity<String>("Etiquette créée", HttpStatus.CREATED);
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

    @GetMapping("/utilisateur/{email}")
    public List<EtiquetteDto> trouverEtiquetteParEmailUtilisateur(@PathVariable("email") String email) {
        return this.etiquetteMapper
                .mapListEntityToDto(
                        this.etiquetteService
                                .trouverEtiquettesParEmailUtilisateur(email));
    }
}

