package org.epita.exposition.selection;

import org.epita.application.selection.SerieSelectionneeService;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("serieselectionnee")
public class SerieSelectionneeController {
    SerieSelectionneeService serieSelectionneeService;

    public SerieSelectionneeController(SerieSelectionneeService serieSelectionneeService) {
        this.serieSelectionneeService = serieSelectionneeService;
    }

    @PostMapping
    public void creerSerieSelectionnee(@RequestBody SerieSelectionneeEntity serieSelectionneeEntity) {
        this.serieSelectionneeService.creerSerieSelectionnee(serieSelectionneeEntity);
    }

    @GetMapping("/{id}")
    public Optional<SerieSelectionneeEntity> trouverSerieSelectionneeParId(@PathVariable("id") Long id) {
        return this.serieSelectionneeService.trouverSerieSelectionneeParId(id);
    }

    @GetMapping
    public List<SerieSelectionneeEntity> trouverToutesLesSeriesSelectionnees() {
        return  this.serieSelectionneeService.trouverToutesLesSeriesSelectionnees();
    }

    @DeleteMapping("{id}")
    public void supprimerSerieSelectionneeParId(@PathVariable("id") Long id) {
        this.serieSelectionneeService.supprimerSerieSelectionneeParId(id);
    }
}
