package org.epita.exposition.controller.media;

import org.epita.application.media.serie.SerieService;
import org.epita.domaine.media.SerieEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.SerieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieController {
    private SerieService serieService;
    private Mapper<SerieEntity, SerieDto> serieMapper;

    public SerieController(SerieService serieService, Mapper<SerieEntity, SerieDto> serieMapper) {
        this.serieService = serieService;
        this.serieMapper = serieMapper;
    }

/*
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
*/

    @PostMapping
    public ResponseEntity<String> creerSerie(@RequestBody SerieDto serieDto) {
        this.serieService
                .creerSerie(
                        this.serieMapper.mapDtoToEntity(serieDto));

        return new ResponseEntity<String>("Série créée", HttpStatus.CREATED);
    }

/*
    @GetMapping("/{id}")
    public SerieDto trouverSerieParId(@PathVariable("id") Long id) {
        return this.serieMapper
                .mapEntityToDto(
                        this.serieService.trouverSerieParId(id));
    }
*/

/*
    @GetMapping
    public List<SerieDto> trouverToutesLesSeries() {
        return this.serieMapper
                .mapListEntityToDto(this.serieService.trouverToutesLesSeries());
    }
*/

/*    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerSerieParId(@PathVariable("id") Long id) {
        this.serieService.supprimerSerieParId(id);

        return new ResponseEntity<String>("Série supprimée", HttpStatus.OK);
    }*/
}
