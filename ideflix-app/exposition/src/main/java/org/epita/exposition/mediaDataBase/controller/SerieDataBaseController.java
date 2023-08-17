package org.epita.exposition.mediaDataBase.controller;

import org.epita.application.mediaDataBase.serieDataBase.SerieDataBaseService;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.mapper.MovieDataBaseMapper;
import org.epita.exposition.mediaDataBase.mapper.SerieDataBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MovieDataBase")
public class SerieDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SerieDataBaseController.class);

    private final SerieDataBaseService serieDataBaseService;

    private final SerieDataBaseMapper serieDataBaseMapper;


    public SerieDataBaseController(SerieDataBaseService serieDataBaseService, SerieDataBaseMapper serieDataBaseMapper) {
        this.serieDataBaseService = serieDataBaseService;
        this.serieDataBaseMapper = serieDataBaseMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/rechercheSerie/{query}")
    public ResponseEntity<List<MediaDataBaseResponseDto>> rechercherMedias(@PathVariable("query") String query){

        logger.debug("IdeFlix - recherche des séries pour " + query);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.serieDataBaseMapper
                        .mapListEntityToDto(
                                this.serieDataBaseService.searchSeries(query)));
    }
}
