package org.epita.exposition.utilisateur.preferences;

import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/preferences")
public class PreferencesUtilisateurController {
    static final Logger logger = LoggerFactory.getLogger(PreferencesUtilisateurController.class);

    private PreferencesUtilisateurService preferencesUtilisateurService;
    private Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper;

    public PreferencesUtilisateurController(PreferencesUtilisateurService preferencesUtilisateurService, PreferencesUtilisateurMapper preferencesUtilisateurMapper) {
        this.preferencesUtilisateurService = preferencesUtilisateurService;
        this.preferencesUtilisateurMapper = preferencesUtilisateurMapper;
    }

    @PostMapping
    public void creerPreferencesUtilisateur(@RequestBody PreferencesUtilisateurDto preferencesUtilisateurDto) {
        this.preferencesUtilisateurService
                .creerPreferencesUtilisateur(
                        this.preferencesUtilisateurMapper.mapDtoToEntity(preferencesUtilisateurDto));
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
