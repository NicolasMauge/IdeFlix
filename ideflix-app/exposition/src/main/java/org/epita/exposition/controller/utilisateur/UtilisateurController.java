package org.epita.exposition.controller.utilisateur;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.utilisateur.UtilisateurDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private UtilisateurService utilisateurService;
    private Mapper<UtilisateurEntity, UtilisateurDto> utilisateurMapper;

    public UtilisateurController(UtilisateurService utilisateurService, Mapper<UtilisateurEntity, UtilisateurDto> utilisateurMapper) {
        this.utilisateurService = utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creerUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        this.utilisateurService
                .creerUtilisateur(this.utilisateurMapper
                        .mapDtoToEntity(utilisateurDto));

        return new ResponseEntity<String>("Utilisateur créé", HttpStatus.CREATED);
    }

    @PostMapping("/masse")
    public ResponseEntity<String> creerPlusieursUtilisateurs(@RequestBody List<UtilisateurDto> utilisateurDtoList) {
        utilisateurDtoList
                .stream()
                .forEach(u -> this.utilisateurService
                                    .creerUtilisateur(
                                            this.utilisateurMapper
                                                .mapDtoToEntity(u)));

        return new ResponseEntity<String>("Utilisateurs créés", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UtilisateurDto trouverUtilisateurParId(@PathVariable("id") Long id) {
        return this.utilisateurMapper
                .mapEntityToDto(this.utilisateurService.trouverUtilisateurParId(id));
    }

    @GetMapping("/email/{email}")
    public UtilisateurDto trouverUtilisateurParEmail(@PathVariable("email") String email) {
        return this.utilisateurMapper
                .mapEntityToDto(this.utilisateurService.trouverUtilisateurParEmail(email));
    }

    @GetMapping
    public List<UtilisateurDto> trouverTousLesUtilisateurs() {
        return this.utilisateurMapper
                .mapListEntityToDto(this.utilisateurService.trouverTousLesUtilisateurs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerUtilisateurParId(@PathVariable("id") Long id) {
        this.utilisateurService.supprimerUtilisateurParId(id);

        return new ResponseEntity<String>("Utilisateur supprimé", HttpStatus.OK);
    }
}
