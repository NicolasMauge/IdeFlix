package org.epita.application.iam.service;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.UtilisateurIamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurIamServiceImpl implements UtilisateurIamService {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurIamServiceImpl.class);

    UtilisateurIamRepository utilisateurIamRepository;
    UtilisateurService utilisateurService;

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository, UtilisateurService utilisateurService) {
        this.utilisateurIamRepository = utilisateurIamRepository;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity) {
        logger.info("APP - IAM - Création utilisateur : " + utilisateurIamEntity.getEmail() + ".");

        // Création dans l'IAM :
        UtilisateurIamEntity nouvelUtilisateurIam = utilisateurIamRepository.creerUtilisateurIam(utilisateurIamEntity);

        // Création dans l'APP :
        UtilisateurEntity utilisateur = new UtilisateurEntity(
                nouvelUtilisateurIam.getEmail(),
                nouvelUtilisateurIam.getNom(),
                nouvelUtilisateurIam.getPrenom(),
                nouvelUtilisateurIam.getDateCreation().toLocalDate()
        );

        utilisateurService.creerUtilisateur(utilisateur);
        logger.debug("IdeFlix - Création utilisateur " + utilisateur.getEmail() + " dans l'APP (id=" + utilisateur.getId() + ").");

        return nouvelUtilisateurIam;
    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIamEntity) {
        logger.warn("APP - IAM - login utilisateur : " + utilisateurIamEntity.getEmail() + ".");
        return utilisateurIamRepository.loginIam(utilisateurIamEntity);
    }


    // pour les administrateurs :
    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam() {
        logger.warn("APP - IAM - Récupération de tous les utilisateurs (non implémenté).");
        return null;
    }

    // pour les administrateurs :
    @Override
    public void delUtilisateurIam(String email) {
        logger.debug("APP - IAM - Effacement de l'utilisateur " + email + ". (non implémenté)");

    }
}
