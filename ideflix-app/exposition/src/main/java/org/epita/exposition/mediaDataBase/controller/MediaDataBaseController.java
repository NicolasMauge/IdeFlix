package org.epita.exposition.mediaDataBase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.mediaDataBase.movieDataBase.MovieDataBaseService;
import org.epita.application.mediaDataBase.serieDataBase.SerieDataBaseService;
import org.epita.domaine.common.IamErreurHabilitationException;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.exposition.common.ErrorModel;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.iam.securite.Habilitations;
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
@Tag(name = "Média Database", description = "Endpoints permettant de récupérer les données du fournisseur de données.")
public class MediaDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseController.class);
    private final SerieDataBaseService serieDataBaseService;
    private final SerieDataBaseMapper serieDataBaseMapper;
    private MovieDataBaseService movieDataBaseService;
    private MovieDataBaseMapper movieDataBaseMapper;

    public MediaDataBaseController(MovieDataBaseService movieDataBaseService, MovieDataBaseMapper movieDataBaseMapper, SerieDataBaseService serieDataBaseService, SerieDataBaseMapper serieDataBaseMapper) {
        this.movieDataBaseService = movieDataBaseService;
        this.movieDataBaseMapper = movieDataBaseMapper;
        this.serieDataBaseService = serieDataBaseService;
        this.serieDataBaseMapper = serieDataBaseMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suggestionsFilmEtSerie/{email}/{page}")
    @Operation(summary = "Récupérer les films et séries suggérés",
            method = "trouverSuggestionFilmsEtSeriesParPageSelonPreferences",
            description = "Le fournisseur de données envoie ses suggestions (cumul de films et de séries).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email fourni.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    public ResponseEntity<List<MediaDataBaseResponseDto>> trouverSuggestionFilmsEtSeriesParPageSelonPreferences(
            @PathVariable("email") String email,
            @PathVariable("page") int page) throws IamErreurHabilitationException {

        if (Habilitations.isHabilitationCorrecte(email)) {

            logger.debug("IdeFlix - Recherche des suggestions de films et séries de la page: " + page);

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
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }
}
