package org.epita.exposition.controller.selection;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.FilmSelectionneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmselectionne")
@Tag(name = "Sélection / Film")
public class FilmSelectionneController {
    private FilmSelectionneService filmSelectionneService;
    private UtilisateurService utilisateurService;
    private Mapper<FilmSelectionneEntity, FilmSelectionneDto> filmSelectionneMapper;

    public FilmSelectionneController(FilmSelectionneService filmSelectionneService, UtilisateurService utilisateurService, Mapper<FilmSelectionneEntity, FilmSelectionneDto> filmSelectionneMapper) {
        this.filmSelectionneService = filmSelectionneService;
        this.utilisateurService = utilisateurService;
        this.filmSelectionneMapper = filmSelectionneMapper;
    }

/*
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }
*/

    /*@PostMapping
    public ResponseEntity<String> creerFilmSelectionne(@RequestBody FilmSelectionneDto filmSelectionneDto) {
        this.filmSelectionneService
                .creerFilmSelectionne(
                        this.filmSelectionneMapper.mapDtoToEntity(filmSelectionneDto));

        return new ResponseEntity<String>("Film sélectionné créé", HttpStatus.CREATED);
    }*/

/*
    @GetMapping("/{id}")
    public FilmSelectionneDto trouverFilmSelectionneParId(@PathVariable("id") Long id) {
        return this.filmSelectionneMapper
                .mapEntityToDto(
                    this.filmSelectionneService
                            .trouverFilmSelectionneParId(id));
    }
*/

/*
    @GetMapping
    public List<FilmSelectionneDto> trouverTousLesFilmsSelectionnes() {
        return this.filmSelectionneMapper
                .mapListEntityToDto(
                    this.filmSelectionneService
                            .trouverTousLesFilmsSelectionnes());
    }
*/

/*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerFilmSelectionneParId(@PathVariable("id") Long id) {
        this.filmSelectionneService.supprimerFilmSelectionneParId(id);

        return new ResponseEntity<String>("Film sélectionné supprimé", HttpStatus.OK);
    }
*/

/*    @GetMapping("/utilisateur/{email}")
    public List<FilmSelectionneDto> trouverFilmsSelectionnesParEmailUtilisateur(@PathVariable("email") String email) {
        return this.filmSelectionneMapper
                .mapListEntityToDto(
                        this.filmSelectionneService
                                .trouverFilmsSelectionnesParEmailUtilisateur(email));
    }*/
}
