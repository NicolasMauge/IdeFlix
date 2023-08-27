package org.epita.exposition.controller.media;

import io.swagger.annotations.ApiOperation;
import org.epita.application.media.film.FilmService;
import org.epita.domaine.media.FilmEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.FilmDto;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamCreationReponseDto;
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
    public ResponseEntity<String> creerFilm(@RequestBody FilmDto filmDto) {
        this.filmService
                .creerFilm(
                        this.filmMapper.mapDtoToEntity(filmDto));

        return new ResponseEntity<String>("Film créé", HttpStatus.CREATED);
    }

    /*
    @GetMapping("/{id}")
    public FilmDto trouverFilmParId(@PathVariable("id") Long id) {
        return this.filmMapper
            .mapEntityToDto(
                    this.filmService.trouverFilmParId(id));
    }*/

    @ApiOperation(value = "Lister tous les films stockés en base")
    @GetMapping
    public List<FilmDto> trouverTousLesFilms() {
        return this.filmMapper
                .mapListEntityToDto(
                        this.filmService.trouverTousLesFilms());
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerFilmParId(@PathVariable("id") Long id) {
        this.filmService.supprimerFilmParId(id);

        return new ResponseEntity<String>("Film supprimé", HttpStatus.OK);
    }*/
}
