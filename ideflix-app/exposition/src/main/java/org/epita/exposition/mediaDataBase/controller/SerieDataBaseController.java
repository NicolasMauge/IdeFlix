package org.epita.exposition.mediaDataBase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.mediaDataBase.serieDataBase.SerieDataBaseService;
import org.epita.exposition.common.ErrorModel;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.SerieDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.mapper.SerieDataBaseMapper;
import org.epita.exposition.mediaDataBase.mapper.SerieWithSaisonDataBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MovieDataBase")
@Tag(name = "Média Database")
public class SerieDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SerieDataBaseController.class);

    private final SerieDataBaseService serieDataBaseService;

    private final SerieDataBaseMapper serieDataBaseMapper;

    private final SerieWithSaisonDataBaseMapper serieWithSaisonDataBaseMapper;


    public SerieDataBaseController(SerieDataBaseService serieDataBaseService, SerieDataBaseMapper serieDataBaseMapper, SerieWithSaisonDataBaseMapper serieWithSaisonDataBaseMapper) {
        this.serieDataBaseService = serieDataBaseService;
        this.serieDataBaseMapper = serieDataBaseMapper;
        this.serieWithSaisonDataBaseMapper = serieWithSaisonDataBaseMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @Operation(summary = "Récupérer une liste de séries filtrée chez le fournisseur de données.",
            method = "rechercherMedias",
            description = "La liste de série est filtrée selon les caractères fournis en paramètre."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recherche effectuée avec succès"),
            @ApiResponse(responseCode = "401", description = "recherche non autorisée par API"),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé pour la requête"),
            @ApiResponse(responseCode = "404", description = "Pas de Résultats pour la recherche")
    })
    @GetMapping("/rechercheSerie/{query}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> rechercherMedias(@PathVariable("query") String query) {

        logger.debug("IdeFlix - recherche des séries pour " + query);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.serieDataBaseMapper
                        .mapListEntityToDto(
                                this.serieDataBaseService.searchSeries(query)));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @Operation(summary = "Récupérer les détails d'une série.",
            method = "rechercherMedias",
            description = "Le fournisseur de données envoie les détails de la série demandé."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recherche effectuée avec succès"),
            @ApiResponse(responseCode = "401", description = "recherche non autorisée par API", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé pour la requête", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
            @ApiResponse(responseCode = "404", description = "Pas de Résultats pour la recherche",content = @Content(schema = @Schema(implementation = ErrorModel.class)))
    })
    @GetMapping("/detailSerie/{id}")
    public ResponseEntity<SerieDataBaseResponseDto> trouverSerieSelonId(@PathVariable("id") long id)  {

        logger.debug("IdeFlix - recherche du détail d'une série pour Id: " + id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.serieWithSaisonDataBaseMapper
                            .mapEntityToDto(
                                    this.serieDataBaseService.findSerieById(id)));
    }

/*    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suggestionsSerie/{page}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> trouverSuggestionSeriesParPage(@PathVariable("page") int page) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.serieDataBaseMapper
                        .mapListEntityToDto(
                                this.serieDataBaseService.searchSuggestedSeries(page)));
    }*/

/*    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/suggestionsSerie/{email}/{page}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> trouverSuggestionSeriesParPageSelonPreferences(
            @PathVariable("email") String email,
            @PathVariable("page") int page) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.serieDataBaseMapper
                        .mapListEntityToDto(
                                this.serieDataBaseService.searchSuggestedSeriesSelonPreferences(email, page)));
    }*/
}
