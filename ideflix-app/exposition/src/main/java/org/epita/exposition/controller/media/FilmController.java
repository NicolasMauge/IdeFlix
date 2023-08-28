package org.epita.exposition.controller.media;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.epita.application.media.film.FilmService;
import org.epita.domaine.media.FilmEntity;
import org.epita.exposition.common.ErrorModel;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.FilmDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
@Tag(name = "Média", description = "Endpoints permettant de gérer les données communes entre les utilisateurs au sein d'IdeFlix.")
public class FilmController {
    private FilmService filmService;
    private Mapper<FilmEntity, FilmDto> filmMapper;

    public FilmController(FilmService filmService, Mapper<FilmEntity, FilmDto> filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    /*
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
*/
    @PostMapping
    @Operation(summary = "Ajout d'un film dans IdeFlix",
            method = "creerFilm",
            description = "Les données de ce film serviront de référence pour tous les utilisateurs qui ajouteront ce film.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK.", content = @Content(schema = @Schema(implementation = String.class)))
    })
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

/*    @Operation(summary = "Lister tous les films stockés en base")
    @GetMapping
    public List<FilmDto> trouverTousLesFilms() {
        return this.filmMapper
                .mapListEntityToDto(
                        this.filmService.trouverTousLesFilms());
    }*/

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerFilmParId(@PathVariable("id") Long id) {
        this.filmService.supprimerFilmParId(id);

        return new ResponseEntity<String>("Film supprimé", HttpStatus.OK);
    }*/
}
