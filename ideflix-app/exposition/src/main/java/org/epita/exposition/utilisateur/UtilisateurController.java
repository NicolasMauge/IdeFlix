package org.epita.exposition.utilisateur;

import org.epita.application.utilisateur.UtilisateurService;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public void creerUtilisateur(@RequestBody UtilisateurEntity utilisateurEntity) {
        this.utilisateurService.creerUtilisateur(utilisateurEntity);
    }

    @GetMapping("/{id}")
    public Optional<UtilisateurEntity> trouverUtilisateurParId(@PathVariable("id") Long id) {
        return this.utilisateurService.trouverUtilisateurParId(id);
    }

    @GetMapping
    public List<UtilisateurEntity> trouverTousLesUtilisateurs() {
        return this.utilisateurService.trouverTousLesUtilisateurs();
    }

    @DeleteMapping("/{id}")
    void supprimerUtilisateurParId(@PathVariable("id") Long id) {
        this.utilisateurService.supprimerUtilisateurParId(id);
    }
}
