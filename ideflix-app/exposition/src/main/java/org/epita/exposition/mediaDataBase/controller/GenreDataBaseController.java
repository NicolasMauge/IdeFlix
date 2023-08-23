package org.epita.exposition.mediaDataBase.controller;

import org.epita.application.mediaDataBase.genreDataBase.GenreDataBaseService;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.mapper.GenreDataBaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/MovieDataBase")
public class GenreDataBaseController {

    private static final Logger logger = LoggerFactory.getLogger(GenreDataBaseController.class);

    private GenreDataBaseService genreDataBaseService;

    private GenreDataBaseMapper genreDataBaseMapper;


    public GenreDataBaseController(GenreDataBaseService genreDataBaseService, GenreDataBaseMapper genreDataBaseMapper) {
        this.genreDataBaseService = genreDataBaseService;
        this.genreDataBaseMapper = genreDataBaseMapper;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/rechercheListeGenres")
    public ResponseEntity<List<GenreDataBaseResponseDto>> rechercherTousLesGenres(){

        logger.debug("IdeFlix - controller- recherche de la liste des tous les genres: " );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.genreDataBaseMapper
                        .mapListEntityToDto(
                                this.genreDataBaseService.searchAllGenres()));
    }
}
