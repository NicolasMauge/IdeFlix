package org.epita.exposition.media.genre;

import org.epita.application.media.genre.GenreService;
import org.epita.application.media.genre.GenreServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurEtPrefDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private GenreService genreService;
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public GenreController(GenreServiceImpl genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creerGenre(@RequestBody GenreDto genreDto) {
        this.genreService
                .creerGenre(
                        this.genreMapper.mapDtoToEntity(genreDto));

        return new ResponseEntity<String>("Genre créé", HttpStatus.CREATED);
    }

    @PostMapping("/masse")
    public ResponseEntity<String> creerPlusieursGenres(@RequestBody List<GenreDto> genreDtoList) {
        genreDtoList
                .stream()
                .forEach(g -> this.genreService
                        .creerGenre(
                                this.genreMapper
                                        .mapDtoToEntity(g)));

        return new ResponseEntity<String>("Genres créés", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GenreDto trouverGenreParId(@PathVariable("id") Long id) {
        return this.genreMapper
            .mapEntityToDto(
                this.genreService.trouverGenreParId(id));
    }

    @GetMapping
    public List<GenreDto> trouverTousLesGenres(){
        return this.genreMapper
                .mapListEntityToDto(
                        this.genreService.trouverTousLesGenres());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerGenre(@PathVariable("id") Long id) {
        this.genreService.supprimerGenreParId(id);

        return new ResponseEntity<String>("Genre supprimé", HttpStatus.OK);
    }
}
