package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.application.UtilisateurIamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iam/utilisateur-iam")
public class UtilisateurIamController {

    UtilisateurIamService utilisateurIamService;
    UtilisateurIamConvertisseur utilisateurIamConvertisseur;

    public UtilisateurIamController(UtilisateurIamService utilisateurIamService, UtilisateurIamConvertisseur utilisateurIamConvertisseur) {
        this.utilisateurIamService = utilisateurIamService;
        this.utilisateurIamConvertisseur = utilisateurIamConvertisseur;
    }

    @PostMapping
    public UtilisateurIamDto creerUtilisateurIam(@RequestBody UtilisateurIamDetailDto utilisateurIamDetailDto) {
        return utilisateurIamConvertisseur.convertirEntiteVersDto(
                utilisateurIamService.creerUtilisateurIam(
                        utilisateurIamConvertisseur.convertirDetailDtoVersEntite(utilisateurIamDetailDto)));

    }

    @GetMapping
    public List<UtilisateurIamDto> lireUtilisateursIam() {
        return utilisateurIamService
                .recupererTousUtilisateurIam()
                .stream()
                .map(utilisateurIam -> utilisateurIamConvertisseur.convertirEntiteVersDto(utilisateurIam))
                .toList();

    }


}
