package org.epita.exposition.controller.selection;

import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.SerieSelectionneeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serieselectionnee")
public class SerieSelectionneeController {
    private SerieSelectionneeService serieSelectionneeService;
    private UtilisateurService utilisateurService;
    private Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> serieSelectionneeMapper;

    public SerieSelectionneeController(SerieSelectionneeService serieSelectionneeService, UtilisateurService utilisateurService, Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> serieSelectionneeMapper) {
        this.serieSelectionneeService = serieSelectionneeService;
        this.utilisateurService = utilisateurService;
        this.serieSelectionneeMapper = serieSelectionneeMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creerSerieSelectionnee(@RequestBody SerieSelectionneeDto serieSelectionneeDto) {
        this.serieSelectionneeService
                .creerSerieSelectionnee(
                        this.serieSelectionneeMapper.mapDtoToEntity(serieSelectionneeDto));

        return new ResponseEntity<String>("Série sélectionnée créée", HttpStatus.CREATED);
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
    public ResponseEntity<String> supprimerSerieSelectionneeParId(@PathVariable("id") Long id) {
        this.serieSelectionneeService.supprimerSerieSelectionneeParId(id);

        return new ResponseEntity<String>("Série sélectionnée supprimée", HttpStatus.OK);
    }

    @GetMapping("/utilisateur/{email}")
    public List<SerieSelectionneeDto> trouverSerieSelectionneesParEmailUtilisateur(@PathVariable("email") String email) {
        return this.serieSelectionneeMapper
                .mapListEntityToDto(
                    this.serieSelectionneeService
                        .trouverSeriesSelectionneesParEmailUtilisateur(email));
    }
}
