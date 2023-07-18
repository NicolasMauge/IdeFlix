package org.epita.exposition.selection.filmselectionne;

import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("filmselectionne")
public class FilmSelectionneController {
    private FilmSelectionneService filmSelectionneService;
    private UtilisateurService utilisateurService;
    private Mapper<FilmSelectionneEntity, FilmSelectionneDto> filmSelectionneMapper;

    public FilmSelectionneController(FilmSelectionneService filmSelectionneService, UtilisateurService utilisateurService, Mapper<FilmSelectionneEntity, FilmSelectionneDto> filmSelectionneMapper) {
        this.filmSelectionneService = filmSelectionneService;
        this.utilisateurService = utilisateurService;
        this.filmSelectionneMapper = filmSelectionneMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public void creerFilmSelectionne(@RequestBody FilmSelectionneDto filmSelectionneDto) {
        this.filmSelectionneService
                .creerFilmSelectionne(
                        this.filmSelectionneMapper.mapDtoToEntity(filmSelectionneDto));
    }

    @GetMapping("/{id}")
    public FilmSelectionneDto trouverFilmSelectionneParId(@PathVariable("id") Long id) {
        return this.filmSelectionneMapper
                .mapEntityToDto(
                    this.filmSelectionneService
                            .trouverFilmSelectionneParId(id));
    }

    @GetMapping
    public List<FilmSelectionneDto> trouverTousLesFilmsSelectionnes() {
        return this.filmSelectionneMapper
                .mapListEntityToDto(
                    this.filmSelectionneService
                            .trouverTousLesFilmsSelectionnes());
    }

    @DeleteMapping("/{id}")
    public void supprimerFilmSelectionneParId(@PathVariable("id") Long id) {
        this.filmSelectionneService.supprimerFilmSelectionneParId(id);
    }

    @GetMapping("/utilisateur/{id}")
    public List<FilmSelectionneDto> trouverFilmSelectionneParUtilisateur(@PathVariable("id") Long id) {
        return this.filmSelectionneMapper
                .mapListEntityToDto(
                    this.filmSelectionneService
                        .trouverFilmSelectionneeParUtilisateur(
                                this.utilisateurService
                                        .trouverUtilisateurParId(id)));
    }
}
