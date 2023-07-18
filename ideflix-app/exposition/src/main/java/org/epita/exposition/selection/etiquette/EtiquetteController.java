package org.epita.exposition.selection.etiquette;

import org.epita.application.selection.etiquette.EtiquetteService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creerEtiquette(@RequestBody EtiquetteDto etiquetteDto) {
        this.etiquetteService
                .creerEtiquette(
                        etiquetteMapper.mapDtoToEntity(etiquetteDto));

        return new ResponseEntity<String>("Etiquette créée", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public EtiquetteDto trouverEtiquetteParId(@PathVariable("id") Long id) {
        return this.etiquetteMapper
                .mapEntityToDto(
                        this.etiquetteService.trouverEtiquetteParId(id));
    }

    @GetMapping
    public List<EtiquetteDto> trouverToutesLesEtiquettes() {
        return this.etiquetteMapper
                .mapListEntityToDto(
                        this.etiquetteService.trouverToutesLesEtiquettes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEtiquetteParId(@PathVariable("id") Long id) {
        this.etiquetteService.supprimerEtiquetteParId(id);

        return new ResponseEntity<String>("Etiquette supprimée", HttpStatus.OK);
    }

    @GetMapping("/utilisateur/{id}")
    public List<EtiquetteDto> trouverEtiquetteParUtilisateur(@PathVariable("id") Long id) {
        return this.etiquetteMapper
                .mapListEntityToDto(
                        this.etiquetteService
                                .trouverEtiquetteParUtilisateur(
                                    this.utilisateurService
                                            .trouverUtilisateurParId(id)));
    }
}

