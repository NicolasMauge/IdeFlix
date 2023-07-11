package org.epita.exposition.media;

import org.epita.application.media.SerieService;
import org.epita.domaine.media.SerieEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/serie")
public class SerieController {
    SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @PostMapping
    public void creerSerie(@RequestBody SerieEntity serieEntity){
        this.serieService.creerSerie(serieEntity);
    }

    @GetMapping("/{id}")
    public Optional<SerieEntity> trouverSerieParId(@PathVariable("id") Long id) {
        return this.serieService.trouverSerieParId(id);
    }

    @GetMapping
    public List<SerieEntity> trouverToutesLesSeries() {
        return this.serieService.trouverToutesLesSeries();
    }

    @DeleteMapping("/{id}")
    public void supprimerSerieParId(@PathVariable("id") Long id) {
        this.serieService.supprimerSerieParId(id);
    }
}
