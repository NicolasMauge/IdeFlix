package org.epita.application.iam.service;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.UtilisateurIamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurIamServiceImpl implements UtilisateurIamService {

    UtilisateurIamRepository utilisateurIamRepository;

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository) {
        this.utilisateurIamRepository = utilisateurIamRepository;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity) {
        return utilisateurIamRepository.creerUtilisateurIam(utilisateurIamEntity);
    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIamEntity) {
        return utilisateurIamRepository.loginIam(utilisateurIamEntity);
    }


    // pour les administrateurs :
    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam() {
        return null;
    }

    // pour les administrateurs :
    @Override
    public void delUtilisateurIam(String email) {

    }
}
