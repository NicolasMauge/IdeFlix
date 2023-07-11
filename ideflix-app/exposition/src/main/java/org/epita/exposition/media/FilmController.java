package org.epita.exposition.media;

import org.epita.application.media.FilmService;
import org.epita.domaine.media.FilmEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/film")
public class FilmController {
    FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public void creerFilm(@RequestBody FilmEntity filmEntity) {
        this.filmService.creerFilm(filmEntity);
    }

    @GetMapping("/{id}")
    public Optional<FilmEntity> trouverFilmParId(@PathVariable("id") Long id) {
        return this.filmService.trouverFilmParId(id);
    }

    @GetMapping
    public List<FilmEntity> trouverTousLesFilms() {
        return this.filmService.trouverTousLesFilms();
    }

    @DeleteMapping("/{id}")
    public void supprimerFilmParId(@PathVariable("id") Long id) {
        this.filmService.supprimerFilmParId(id);
    }
}
