package org.epita.ideflixiam.exposition.utilisateur;

import org.epita.ideflixiam.application.common.Util;
import org.epita.ideflixiam.application.utilisateur.UtilisateurService;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

import static org.epita.ideflixiam.application.common.Util.ROLE_ADMIN;
import static org.epita.ideflixiam.application.common.Util.ROLE_UTILISATEUR;

@RestController
@RequestMapping
public class UtilisateurController {

    Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    UtilisateurService utilisateurService;
    UtilisateurConvertisseur utilisateurConvertisseur;

    @Value("${org.epita.ideflixiam.admin.email}")
    private String emailAdmin;

    @Value("${org.epita.ideflixiam.admin.lastname}")
    private String nomAdmin;

    @Value("${org.epita.ideflixiam.admin.firstname}")
    private String prenomAdmin;

    @Value("${org.epita.ideflixiam.admin.password}")
    private String motDePasseAdmin;

    public UtilisateurController(UtilisateurService utilisateurService, UtilisateurConvertisseur utilisateurConvertisseur) {
        this.utilisateurService = utilisateurService;
        this.utilisateurConvertisseur = utilisateurConvertisseur;

//        // s'assurer que le compte admin existe, sinon, le créer :
//        UtilisateurEntity utilisateur = utilisateurService.recupererUtilisateurParEmail(emailAdmin);
//        if (utilisateur == null) {
//            logger.debug("Création du premier administrateur");
//            utilisateurService.creerPremierAdministrateur(nomAdmin, prenomAdmin, emailAdmin, motDePasseAdmin);
//        }
    }


    @PostMapping("/utilisateur")
    public UtilisateurSimpleDto creerUtilisateur(@RequestBody UtilisateurEntreeDto utilisateurEntreeDto) {

        logger.debug("Creation utilisateur : " + utilisateurEntreeDto.getEmail());

        return utilisateurConvertisseur.convertirEntiteVersSimpleDto(
                utilisateurService.creerUtilisateur(
                        utilisateurConvertisseur.convertirDetailDtoVersEntite(utilisateurEntreeDto,
                                ROLE_UTILISATEUR))
        );

    }

    @GetMapping("/admin/utilisateurs")
    public List<UtilisateurDetailDto> lireUtilisateurs() {

        logger.debug("Récupération de tous utilisateurs");

        return utilisateurService
                .recupererUtilisateurs()
                .stream()
                .map(utilisateur -> utilisateurConvertisseur.convertirEntiteVersDetailDto(utilisateur))
                .toList();

    }


}
