package org.epita.exposition.controller.utilisateur;

import org.epita.application.media.genre.GenreService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/preferences")
public class PreferencesUtilisateurController {
    static final Logger logger = LoggerFactory.getLogger(PreferencesUtilisateurController.class);

    private PreferencesUtilisateurService preferencesUtilisateurService;
    private GenreService genreService;
    private Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper;
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public PreferencesUtilisateurController(PreferencesUtilisateurService preferencesUtilisateurService, GenreService genreService, Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper, Mapper<GenreEntity, GenreDto> genreMapper) {
        this.preferencesUtilisateurService = preferencesUtilisateurService;
        this.genreService = genreService;
        this.preferencesUtilisateurMapper = preferencesUtilisateurMapper;
        this.genreMapper = genreMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creerPreferencesUtilisateur(@RequestBody PreferencesUtilisateurDto preferencesUtilisateurDto) {
        this.preferencesUtilisateurService
                .creerPreferencesUtilisateur(
                        this.preferencesUtilisateurMapper.mapDtoToEntity(preferencesUtilisateurDto));

        return new ResponseEntity<String>("Preference utilisateur créée", HttpStatus.CREATED);
    }

    @PostMapping("/masse")
    public ResponseEntity<String> creerPlusieursPreferences(@RequestBody List<PreferencesUtilisateurDto> preferencesUtilisateurDtoList) {
        preferencesUtilisateurDtoList
                .stream()
                .forEach(p -> this.preferencesUtilisateurService
                        .creerPreferencesUtilisateur(
                                this.preferencesUtilisateurMapper
                                        .mapDtoToEntity(p)));

        return new ResponseEntity<String>("Preferences utilisateurs créées", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PreferencesUtilisateurDto trouverPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        return this.preferencesUtilisateurMapper
                .mapEntityToDto(
                        this.preferencesUtilisateurService.trouverPreferencesUtilisateurParId(id));
    }

    @GetMapping
    public List<PreferencesUtilisateurDto> trouverToutesLesPreferencesUtilisateurs() {
        return this.preferencesUtilisateurMapper
                .mapListEntityToDto(
                        this.preferencesUtilisateurService.trouverToutesLesPreferencesUtilisateurs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        this.preferencesUtilisateurService.supprimerPreferencesUtilisateurParId(id);

        return new ResponseEntity<String>("Preference utilisateur supprimée", HttpStatus.OK);
    }

    @GetMapping("/utilisateur/{email}")
    public PreferencesUtilisateurDto trouverPreferencesUtilisateurParEmailUtilisateur(@PathVariable("email") String email) {
        return this.preferencesUtilisateurMapper
                .mapEntityToDto(
                    this.preferencesUtilisateurService
                            .trouverPreferenceUtilisateurParEmailUtilisateur(email));
    }
}
