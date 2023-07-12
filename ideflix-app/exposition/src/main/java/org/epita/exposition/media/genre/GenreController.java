package org.epita.exposition.media.genre;

import org.epita.application.media.GenreServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private GenreServiceImpl genreService;
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public GenreController(GenreServiceImpl genreService, GenreMapper genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @PostMapping
    public void creerGenre(@RequestBody GenreDto genreDto) {
        this.genreService
                .creerGenre(
                        this.genreMapper.mapDtoToEntity(genreDto));
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
    public void supprimerGenre(@PathVariable("id") Long id) {
        this.genreService.supprimerGenreParId(id);
    }
}
