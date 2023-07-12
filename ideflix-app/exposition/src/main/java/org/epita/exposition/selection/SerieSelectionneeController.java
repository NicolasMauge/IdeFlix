package org.epita.exposition.selection;

import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("serieselectionnee")
public class SerieSelectionneeController {
    SerieSelectionneeService serieSelectionneeService;
    UtilisateurService utilisateurService;

    public SerieSelectionneeController(SerieSelectionneeService serieSelectionneeService, UtilisateurService utilisateurService) {
        this.serieSelectionneeService = serieSelectionneeService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public void creerSerieSelectionnee(@RequestBody SerieSelectionneeEntity serieSelectionneeEntity) {
        this.serieSelectionneeService.creerSerieSelectionnee(serieSelectionneeEntity);
    }

    @GetMapping("/{id}")
    public SerieSelectionneeEntity trouverSerieSelectionneeParId(@PathVariable("id") Long id) {
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

    @GetMapping("/utilisateur/{id}")
    public List<SerieSelectionneeEntity> trouverFilmSelectionneeParUtilisateur(@PathVariable("id") Long id) {
        return this.serieSelectionneeService
                .trouverSerieParUtilisateur(this.utilisateurService.trouverUtilisateurParId(id));
    }
}
