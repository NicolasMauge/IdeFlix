package org.epita.exposition.selection;

import org.epita.application.selection.EtiquetteService;
import org.epita.domaine.selection.EtiquetteEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etiquette")
public class EtiquetteController {
    EtiquetteService etiquetteService;

    public EtiquetteController(EtiquetteService etiquetteService) {
        this.etiquetteService = etiquetteService;
    }

    @PostMapping
    public void creerEtiquette(@RequestBody EtiquetteEntity etiquetteEntity) {
        this.etiquetteService.creerEtiquette(etiquetteEntity);
    }

    @GetMapping("/{id}")
    public Optional<EtiquetteEntity> trouverEtiquetteParId(@PathVariable("id") Long id) {
        return this.etiquetteService.trouverEtiquetteParId(id);
    }

    @GetMapping
    public List<EtiquetteEntity> trouverToutesLesEtiquettes() {
        return this.etiquetteService.trouverToutesLesEtiquettes();
    }

    @DeleteMapping("/{id}")
    public void supprimerEtiquetteParId(Long id) {
        this.etiquetteService.supprimerEtiquetteParId(id);
    }
}

