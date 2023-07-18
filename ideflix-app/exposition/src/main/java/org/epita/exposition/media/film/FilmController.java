package org.epita.exposition.media.film;

import org.epita.application.media.film.FilmService;
import org.epita.domaine.media.FilmEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    private FilmService filmService;
    private Mapper<FilmEntity, FilmDto> filmMapper;

    public FilmController(FilmService filmService, Mapper<FilmEntity, FilmDto> filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public void creerFilm(@RequestBody FilmDto filmDto) {
        this.filmService
                .creerFilm(
                        this.filmMapper.mapDtoToEntity(filmDto));
    }

    @GetMapping("/{id}")
    public FilmDto trouverFilmParId(@PathVariable("id") Long id) {
        return this.filmMapper
            .mapEntityToDto(
                    this.filmService.trouverFilmParId(id));
    }

    @GetMapping
    public List<FilmDto> trouverTousLesFilms() {
        return this.filmMapper
            .mapListEntityToDto(
                this.filmService.trouverTousLesFilms());
    }

    @DeleteMapping("/{id}")
    public void supprimerFilmParId(@PathVariable("id") Long id) {
        this.filmService.supprimerFilmParId(id);
    }
}
