package org.epita.exposition.utilisateur.utilisateur;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private UtilisateurService utilisateurService;
    private Mapper<UtilisateurEntity, UtilisateurDto> utilisateurMapper;

    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurMapper utilisateurMapper) {
        this.utilisateurService = utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
    }

    @PostMapping
    public void creerUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        this.utilisateurService
                .creerUtilisateur(this.utilisateurMapper.mapDtoToEntity(utilisateurDto));
    }

    @GetMapping("/{id}")
    public UtilisateurDto trouverUtilisateurParId(@PathVariable("id") Long id) {
        return this.utilisateurMapper
                .mapEntityToDto(this.utilisateurService.trouverUtilisateurParId(id));
    }

    @GetMapping
    public List<UtilisateurDto> trouverTousLesUtilisateurs() {
        return this.utilisateurMapper
                .mapListEntityToDto(this.utilisateurService.trouverTousLesUtilisateurs());
    }

    @DeleteMapping("/{id}")
    void supprimerUtilisateurParId(@PathVariable("id") Long id) {
        this.utilisateurService.supprimerUtilisateurParId(id);
    }
}
