package org.epita.exposition.utilisateur.preferences;

import org.epita.application.media.genre.GenreService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurEtPrefDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    public void creerPreferencesUtilisateur(@RequestBody PreferencesUtilisateurDto preferencesUtilisateurDto) {
        this.preferencesUtilisateurService
                .creerPreferencesUtilisateur(
                        this.preferencesUtilisateurMapper.mapDtoToEntity(preferencesUtilisateurDto));
    }

    @PostMapping("/masse")
    public void creerPlusieursPréférences(@RequestBody List<PreferencesUtilisateurDto> preferencesUtilisateurDtoList) {
        preferencesUtilisateurDtoList
                .stream()
                .forEach(p -> this.preferencesUtilisateurService
                        .creerPreferencesUtilisateur(
                                this.preferencesUtilisateurMapper
                                        .mapDtoToEntity(p)));
    }

    @PostMapping("/addgenre/{id}/{genreid}")
    public void ajouterGenrePourId(@PathVariable("id") Long id, @PathVariable("genreid") Long genreId)  {
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = preferencesUtilisateurService.trouverPreferencesUtilisateurParId(id);
        List<GenreEntity> genreEntityList = preferencesUtilisateurEntity.getGenreList();
        GenreEntity genreEntity = genreService.trouverGenreParId(genreId);

        if(genreEntityList.size()==0) {
            genreEntityList.add(genreEntity);

            preferencesUtilisateurEntity.setGenreList(genreEntityList);
            preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateurEntity);
        } else {
            if(!genreEntityList.contains(genreEntity)) {
                genreEntityList.add(genreEntity);

                preferencesUtilisateurEntity.setGenreList(genreEntityList);
                preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateurEntity);
            }
        }
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
    public void supprimerPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        this.preferencesUtilisateurService.supprimerPreferencesUtilisateurParId(id);
    }
}
