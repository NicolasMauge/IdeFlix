package org.epita.exposition.selection.serieselectionnee;

import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("serieselectionnee")
public class SerieSelectionneeController {
    private SerieSelectionneeService serieSelectionneeService;
    private UtilisateurService utilisateurService;
    private Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> serieSelectionneeMapper;

    public SerieSelectionneeController(SerieSelectionneeService serieSelectionneeService, UtilisateurService utilisateurService, Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> serieSelectionneeMapper) {
        this.serieSelectionneeService = serieSelectionneeService;
        this.utilisateurService = utilisateurService;
        this.serieSelectionneeMapper = serieSelectionneeMapper;
    }

    @PostMapping
    public void creerSerieSelectionnee(@RequestBody SerieSelectionneeDto serieSelectionneeDto) {
        this.serieSelectionneeService
                .creerSerieSelectionnee(
                        this.serieSelectionneeMapper.mapDtoToEntity(serieSelectionneeDto));
    }

    @GetMapping("/{id}")
    public SerieSelectionneeDto trouverSerieSelectionneeParId(@PathVariable("id") Long id) {
        return this.serieSelectionneeMapper
                .mapEntityToDto(
                    this.serieSelectionneeService
                            .trouverSerieSelectionneeParId(id));
    }

    @GetMapping
    public List<SerieSelectionneeDto> trouverToutesLesSeriesSelectionnees() {
        return this.serieSelectionneeMapper
                .mapListEntityToDto(
                    this.serieSelectionneeService
                            .trouverToutesLesSeriesSelectionnees());
    }

    @DeleteMapping("{id}")
    public void supprimerSerieSelectionneeParId(@PathVariable("id") Long id) {
        this.serieSelectionneeService.supprimerSerieSelectionneeParId(id);
    }

    @GetMapping("/utilisateur/{id}")
    public List<SerieSelectionneeDto> trouverSerieSelectionneeParUtilisateur(@PathVariable("id") Long id) {
        return this.serieSelectionneeMapper
                .mapListEntityToDto(
                    this.serieSelectionneeService
                        .trouverSerieParUtilisateur(
                                this.utilisateurService
                                        .trouverUtilisateurParId(id)));
    }
}
