package org.epita.exposition.mediaDataBase.controller;

import org.epita.application.mediaDataBase.movieDataBase.MovieDataBaseService;
import org.epita.application.mediaDataBase.serieDataBase.SerieDataBaseService;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.mapper.MovieDataBaseMapper;
import org.epita.exposition.mediaDataBase.mapper.SerieDataBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/MovieDataBase")
public class MediaDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseController.class);

    private MovieDataBaseService movieDataBaseService;

    private MovieDataBaseMapper movieDataBaseMapper;

    private final SerieDataBaseService serieDataBaseService;

    private final SerieDataBaseMapper serieDataBaseMapper;

    public MediaDataBaseController(MovieDataBaseService movieDataBaseService, MovieDataBaseMapper movieDataBaseMapper, SerieDataBaseService serieDataBaseService, SerieDataBaseMapper serieDataBaseMapper) {
        this.movieDataBaseService = movieDataBaseService;
        this.movieDataBaseMapper = movieDataBaseMapper;
        this.serieDataBaseService = serieDataBaseService;
        this.serieDataBaseMapper = serieDataBaseMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suggestionsFilmEtSerie/{email}/{page}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> trouverSuggestionFilmsEtSeriesParPageSelonPreferences(
            @PathVariable("email") String email,
            @PathVariable("page") int page) {

        logger.debug("IdeFlix - recherche Des suggestions de films et sréies de la page: " + page);

        List<MediaDataBaseResponseDto> movieDataBaseList = this.movieDataBaseMapper
                        .mapListEntityToDto(this.movieDataBaseService
                        .searchSuggestedMoviesSelonPreferences(email, page));
        List<MediaDataBaseResponseDto> serieDataBaseList = this.serieDataBaseMapper
                        .mapListEntityToDto(this.serieDataBaseService
                        .searchSuggestedSeriesSelonPreferences(email, page));

        //fusionner les 2 listes et trier par NoteDatabase

        List<MediaDataBaseResponseDto> mergedList = new ArrayList<>();

        mergedList.addAll(movieDataBaseList);
        mergedList.addAll(serieDataBaseList);

        // Triez la liste fusionnée par noteDataBase décroissante

        // avec un comparateur = classe anonyme
//        Collections.sort(mergedList, new Comparator<MediaDataBaseResponseDto>() {
//            @Override
//            public int compare(MediaDataBaseResponseDto media1, MediaDataBaseResponseDto media2) {
//                // Triez en ordre décroissant
//                return Float.compare(media2.getNoteDataBase(), media1.getNoteDataBase());
//            }
//        });

        // OU en  remplacant avec expression lambda
//        Collections.sort(mergedList, (media1, media2) -> {
//            // Triez en ordre décroissant
//            return Float.compare(media2.getNoteDataBase(), media1.getNoteDataBase());
//        });

        // OU on peut remplacer avec référence de methode
        Collections.sort(mergedList, Comparator.comparingDouble(MediaDataBaseResponseDto::getNoteDataBase).reversed());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mergedList);
    }
}
