package org.epita.exposition.mediaDataBase.controller;

import org.epita.application.mediaDataBase.movieDataBase.MovieDataBaseService;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.mapper.MovieDataBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MovieDataBase")
public class MovieDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseController.class);

    private MovieDataBaseService movieDataBaseService;

    private MovieDataBaseMapper movieDataBaseMapper;

    private Mapper<MovieDataBase, MediaDataBaseResponseDto> mediaMapper;

    public MovieDataBaseController(MovieDataBaseService movieDataBaseService, MovieDataBaseMapper movieDataBaseMapper, Mapper<MovieDataBase, MediaDataBaseResponseDto> mediaMapper) {
        this.movieDataBaseService = movieDataBaseService;
        this.movieDataBaseMapper = movieDataBaseMapper;
        this.mediaMapper = mediaMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/rechercheFilm/{query}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> rechercherMedias(@PathVariable("query") String query) {

//        logger.debug("IdeFlix - recherche des films pour " + query);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.movieDataBaseMapper
                        .mapListEntityToDto(
                                this.movieDataBaseService.searchMovies(query)));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/detailFilm/{id}")
    public ResponseEntity<MediaDataBaseResponseDto> trouverFilmSelonId(@PathVariable("id") long id) {

//        logger.debug("IdeFlix - recherche du détail d'un film pour Id: " + id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.movieDataBaseMapper
                        .mapEntityToDto(
                                this.movieDataBaseService.findMovieById(id)));
    }

/*    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suggestionsFilm/{page}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> trouverSuggestionFilmsParPage(@PathVariable("page") int page) {

//        logger.debug("IdeFlix - recherche Des suggestions de films de la page: " + page);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.movieDataBaseMapper
                        .mapListEntityToDto(
                                this.movieDataBaseService.searchSuggestedMovies(page)));
    }*/

/*    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suggestionsFilm/{email}/{page}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> trouverSuggestionFilmsParPageSelonPreferences(
            @PathVariable("email") String email,
            @PathVariable("page") int page) {

//        logger.debug("IdeFlix - recherche Des suggestions de films de la page: " + page);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.movieDataBaseMapper
                        .mapListEntityToDto(
                                this.movieDataBaseService.searchSuggestedMoviesSelonPreferences(email, page)));
    }*/


}
