package org.epita.exposition.utilisateur.utilisateur;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private UtilisateurService utilisateurService;
    private Mapper<UtilisateurEntity, UtilisateurDto> utilisateurMapper;
    private Mapper<UtilisateurEntity, UtilisateurEtPrefDto> utilisateurEtPrefMapper;

    public UtilisateurController(UtilisateurService utilisateurService, Mapper<UtilisateurEntity, UtilisateurDto> utilisateurMapper, Mapper<UtilisateurEntity, UtilisateurEtPrefDto> utilisateurEtPrefMapper) {
        this.utilisateurService = utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurEtPrefMapper = utilisateurEtPrefMapper;
    }

    @GetMapping("/hello")
    public String testOk() {
        return "Hello, World";
    }

    @PostMapping
    public ResponseEntity<String> creerUtilisateur(@RequestBody UtilisateurEtPrefDto utilisateurEtPrefDto) {
        this.utilisateurService
                .creerUtilisateur(this.utilisateurEtPrefMapper
                        .mapDtoToEntity(utilisateurEtPrefDto));

        return new ResponseEntity<String>("Utilisateur créé", HttpStatus.CREATED);
    }

    @PostMapping("/masse")
    public void creerPlusieursUtilisateurs(@RequestBody List<UtilisateurEtPrefDto> utilisateurEtPrefDtoList) {
        utilisateurEtPrefDtoList
                .stream()
                .forEach(u -> this.utilisateurService
                                    .creerUtilisateur(
                                            this.utilisateurEtPrefMapper
                                                .mapDtoToEntity(u)));
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
    void supprimerUtilisateurParId(@PathVariable("id") Long id) {
        this.utilisateurService.supprimerUtilisateurParId(id);
    }

    @GetMapping("/etpref/{id}")
    public UtilisateurEtPrefDto trouverUtilisateurAvecPrefParId(@PathVariable("id") Long id) {
        return this.utilisateurEtPrefMapper
                .mapEntityToDto(
                    this.utilisateurService
                            .trouverUtilisateurParId(id));
    }
}
