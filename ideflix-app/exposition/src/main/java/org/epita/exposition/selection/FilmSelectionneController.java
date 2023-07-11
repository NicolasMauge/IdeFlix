package org.epita.exposition.selection;

import org.epita.application.selection.FilmSelectionneService;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("filmselectionne")
public class FilmSelectionneController {
    FilmSelectionneService filmSelectionneService;

    public FilmSelectionneController(FilmSelectionneService filmSelectionneService) {
        this.filmSelectionneService = filmSelectionneService;
    }

    @PostMapping
    public void creerFilmSelectionne(@RequestBody FilmSelectionneEntity filmSelectionneEntity) {
        this.filmSelectionneService.creerFilmSelectionne(filmSelectionneEntity);
    }

    @GetMapping("/{id}")
    public Optional<FilmSelectionneEntity> trouverFilmSelectionneParId(@PathVariable("id") Long id) {
        return this.filmSelectionneService.trouverFilmSelectionneParId(id);
    }

    @GetMapping
    public List<FilmSelectionneEntity> trouverTousLesFilmsSelectionnes() {
        return this.filmSelectionneService.trouverTousLesFilmsSelectionnes();
    }

    @DeleteMapping("/{id}")
    public void supprimerFilmSelectionneParId(@PathVariable("id") Long id) {
        this.filmSelectionneService.supprimerFilmSelectionneParId(id);
    }
}
