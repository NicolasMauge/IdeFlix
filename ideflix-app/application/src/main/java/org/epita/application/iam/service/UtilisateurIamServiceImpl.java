package org.epita.application.iam.service;

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

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository) {
        this.utilisateurIamRepository = utilisateurIamRepository;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity) {
        logger.info("APP - IAM - Création utilisateur : " + utilisateurIamEntity.getEmail() + ".");

        return utilisateurIamRepository.creerUtilisateurIam(utilisateurIamEntity);
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
