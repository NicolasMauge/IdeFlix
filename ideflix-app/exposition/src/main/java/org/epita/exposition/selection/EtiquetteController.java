package org.epita.exposition.selection;

import org.epita.application.selection.EtiquetteService;
import org.epita.application.utilisateur.UtilisateurService;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etiquette")
public class EtiquetteController {
    EtiquetteService etiquetteService;
    UtilisateurService utilisateurService;

    public EtiquetteController(EtiquetteService etiquetteService, UtilisateurService utilisateurService) {
        this.etiquetteService = etiquetteService;
        this.utilisateurService = utilisateurService;
    }

    @PostMapping
    public void creerEtiquette(@RequestBody EtiquetteEntity etiquetteEntity) {
        this.etiquetteService.creerEtiquette(etiquetteEntity);
    }

    @GetMapping("/{id}")
    public Optional<EtiquetteEntity> trouverEtiquetteParId(@PathVariable("id") Long id) {
        return this.etiquetteService.trouverEtiquetteParId(id);
    }

    @GetMapping
    public List<EtiquetteEntity> trouverToutesLesEtiquettes() {
        return this.etiquetteService.trouverToutesLesEtiquettes();
    }

    @DeleteMapping("/{id}")
    public void supprimerEtiquetteParId(Long id) {
        this.etiquetteService.supprimerEtiquetteParId(id);
    }

    @GetMapping("/utilisateur/{id}")
    public List<EtiquetteEntity> trouverEtiquetteParUtilisateur(@PathVariable("id") Long id) {
        Optional<UtilisateurEntity> utilisateurEntityOptional = this.utilisateurService.trouverUtilisateurParId(id);
        if(utilisateurEntityOptional.isPresent()) {
            return this.etiquetteService.trouverEtiquetteParUtilisateur(utilisateurEntityOptional.get());
        } else {
            // il faudra une classe exception ici EntityNotFoundException
            return null;
        }
    }
}

