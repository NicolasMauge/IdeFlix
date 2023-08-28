package org.epita.exposition.mediaDataBase.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.mediaDataBase.serieDataBase.EpisodeDataBaseService;
import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.EpisodeDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.mapper.EpisodeDataBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MovieDataBase")
@Tag(name = "Média Database")
public class EpisodeDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(EpisodeDataBaseController.class);

    private EpisodeDataBaseService episodeDataBaseService;

    private EpisodeDataBaseMapper episodeDataBaseMapper;

    private Mapper<EpisodeSerieDataBase, EpisodeDataBaseResponseDto> mediaMapper;

    public EpisodeDataBaseController(EpisodeDataBaseService episodeDataBaseService,
                                     EpisodeDataBaseMapper episodeDataBaseMapper,
                                     Mapper<EpisodeSerieDataBase, EpisodeDataBaseResponseDto> mediaMapper) {
        this.episodeDataBaseService = episodeDataBaseService;
        this.episodeDataBaseMapper = episodeDataBaseMapper;
        this.mediaMapper = mediaMapper;
    }

/*    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/detailEpisode/id={id}&saison={saison}&episode={episode}")
    public ResponseEntity<EpisodeDataBaseResponseDto> trouverDetailEpisode(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("id") long id, @PathVariable("saison") int numeroSaison, @PathVariable("episode") int numeroEpisode){

        logger.debug("IdeFlix - recherche du détail d'un épisode numéro: " + numeroEpisode
                + "de la saison: " + numeroSaison
                + "pour la série d'Id: " + id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.episodeDataBaseMapper
                        .mapEntityToDto(
                                this.episodeDataBaseService.findEpisodeByNumberEpisodeAndNumberSeasonAndId(id,numeroSaison,numeroEpisode)));
    }*/

}
