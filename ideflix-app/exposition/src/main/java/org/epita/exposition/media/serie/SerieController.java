package org.epita.exposition.media.serie;

import org.epita.application.media.serie.SerieService;
import org.epita.domaine.media.SerieEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.selection.serieselectionnee.SerieSelectionneeDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/serie")
public class SerieController {
    private SerieService serieService;
    private Mapper<SerieEntity, SerieDto> serieMapper;

    public SerieController(SerieService serieService, Mapper<SerieEntity, SerieDto> serieMapper) {
        this.serieService = serieService;
        this.serieMapper = serieMapper;
    }

    @PostMapping
    public void creerSerie(@RequestBody SerieDto serieDto){
        this.serieService
                .creerSerie(
                        this.serieMapper.mapDtoToEntity(serieDto));
    }

    @GetMapping("/{id}")
    public SerieDto trouverSerieParId(@PathVariable("id") Long id) {
        return this.serieMapper
                .mapEntityToDto(
                        this.serieService.trouverSerieParId(id));
    }

    @GetMapping
    public List<SerieDto> trouverToutesLesSeries() {
        return this.serieMapper
                .mapListEntityToDto(this.serieService.trouverToutesLesSeries());
    }

    @DeleteMapping("/{id}")
    public void supprimerSerieParId(@PathVariable("id") Long id) {
        this.serieService.supprimerSerieParId(id);
    }
}
