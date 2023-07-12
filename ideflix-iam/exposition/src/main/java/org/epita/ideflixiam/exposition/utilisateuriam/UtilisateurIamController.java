package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.application.utilisateuriam.UtilisateurIamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.epita.ideflixiam.application.common.Util.ROLE_UTILISATEUR_STANDARD;

@RestController
@RequestMapping
public class UtilisateurIamController {

    Logger logger = LoggerFactory.getLogger(UtilisateurIamController.class);

    UtilisateurIamService utilisateurIamService;
    UtilisateurIamConvertisseur utilisateurIamConvertisseur;

    public UtilisateurIamController(UtilisateurIamService utilisateurIamService, UtilisateurIamConvertisseur utilisateurIamConvertisseur) {
        this.utilisateurIamService = utilisateurIamService;
        this.utilisateurIamConvertisseur = utilisateurIamConvertisseur;
    }


    @PostMapping("/utilisateur-iam")
    public UtilisateurIamSimpleDto creerUtilisateurIam(@RequestBody UtilisateurIamEntreeDto utilisateurIamEntreeDto) {

        logger.debug("Creation utilisateur : " + utilisateurIamEntreeDto.getEmail());

        return utilisateurIamConvertisseur.convertirEntiteVersSimpleDto(
                utilisateurIamService.creerUtilisateurIam(
                        utilisateurIamConvertisseur.convertirDetailDtoVersEntite(utilisateurIamEntreeDto,
                                ROLE_UTILISATEUR_STANDARD))
        );

    }

    @GetMapping("/admin")
    public List<UtilisateurIamDetailDto> lireUtilisateursIam() {

        logger.debug("Récupération de tous utilisateurs");

        return utilisateurIamService
                .recupererTousUtilisateurIam()
                .stream()
                .map(utilisateurIam -> utilisateurIamConvertisseur.convertirEntiteVersDetailDto(utilisateurIam))
                .toList();

    }


}
