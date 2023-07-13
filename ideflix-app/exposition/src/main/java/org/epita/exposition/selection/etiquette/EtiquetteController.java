package org.epita.exposition.selection.etiquette;

import org.epita.application.selection.etiquette.EtiquetteService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.exposition.common.Mapper;
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

    @PostMapping
    public void creerEtiquette(@RequestBody EtiquetteDto etiquetteDto) {
        this.etiquetteService
                .creerEtiquette(
                        etiquetteMapper.mapDtoToEntity(etiquetteDto));
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
    public void supprimerEtiquetteParId(Long id) {
        this.etiquetteService.supprimerEtiquetteParId(id);
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

