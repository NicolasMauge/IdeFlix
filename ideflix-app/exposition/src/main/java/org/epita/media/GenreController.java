package org.epita.media;

import org.epita.application.media.GenreServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private GenreServiceImpl genreService;

    public GenreController(GenreServiceImpl genreService) {
        this.genreService = genreService;
    }


    @PostMapping
    public void creerGenre(@RequestBody GenreEntity genreEntity) {
        this.genreService.creerGenre(genreEntity);
    }

    @GetMapping("/{id}")
    public Optional<GenreEntity> trouverGenreParId(@PathVariable("id") Long id) {
        return this.genreService.trouverGenreParId(id);
    }

    @GetMapping
    public List<GenreEntity> trouverTousLesGenres(){
        return this.genreService.trouverTousLesGenres();
    }

    @DeleteMapping("/{id}")
    public void supprimerGenre(@PathVariable("id") Long id) {
        this.genreService.supprimerGenreParId(id);
    }
}
