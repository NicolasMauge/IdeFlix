package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.application.UtilisateurIamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/iam/utilisateur-iam")
public class UtilisateurIamController {

    Logger logger = LoggerFactory.getLogger(UtilisateurIamController.class);

    UtilisateurIamService utilisateurIamService;
    UtilisateurIamConvertisseur utilisateurIamConvertisseur;

    public UtilisateurIamController(UtilisateurIamService utilisateurIamService, UtilisateurIamConvertisseur utilisateurIamConvertisseur) {
        this.utilisateurIamService = utilisateurIamService;
        this.utilisateurIamConvertisseur = utilisateurIamConvertisseur;
    }

    @PostMapping
    public UtilisateurIamDto creerUtilisateurIam(@RequestBody UtilisateurIamDetailDto utilisateurIamDetailDto) {

        logger.debug("Creation utilisateur : " + utilisateurIamDetailDto.getEmail());

        return utilisateurIamConvertisseur.convertirEntiteVersDto(
                utilisateurIamService.creerUtilisateurIam(
                        utilisateurIamConvertisseur.convertirDetailDtoVersEntite(utilisateurIamDetailDto)));

    }

    @GetMapping
    public List<UtilisateurIamDto> lireUtilisateursIam() {

        logger.debug("Récupération de tous utilisateurs");

        return utilisateurIamService
                .recupererTousUtilisateurIam()
                .stream()
                .map(utilisateurIam -> utilisateurIamConvertisseur.convertirEntiteVersDto(utilisateurIam))
                .toList();

    }


}
