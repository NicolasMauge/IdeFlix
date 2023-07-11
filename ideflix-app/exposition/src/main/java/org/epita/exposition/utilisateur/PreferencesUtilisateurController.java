package org.epita.exposition.utilisateur;

import org.epita.application.utilisateur.PreferencesUtilisateurService;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preferences")
public class PreferencesUtilisateurController {
    PreferencesUtilisateurService preferencesUtilisateurService;

    public PreferencesUtilisateurController(PreferencesUtilisateurService preferencesUtilisateurService) {
        this.preferencesUtilisateurService = preferencesUtilisateurService;
    }

    @PostMapping
    public void creerPreferencesUtilisateur(@RequestBody PreferencesUtilisateurEntity preferencesUtilisateurEntity) {
        this.preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateurEntity);
    }

    @GetMapping("/{id}")
    public Optional<PreferencesUtilisateurEntity> trouverPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        return this.preferencesUtilisateurService.trouverPreferencesUtilisateurParId(id);
    }

    @GetMapping
    public List<PreferencesUtilisateurEntity> trouverToutesLesPreferencesUtilisateurs() {
        return this.preferencesUtilisateurService.trouverToutesLesPreferencesUtilisateurs();
    }

    @DeleteMapping("/{id}")
    public void supprimerPreferencesUtilisateurParId(@PathVariable("id") Long id) {
        this.preferencesUtilisateurService.supprimerPreferencesUtilisateurParId(id);
    }
}
