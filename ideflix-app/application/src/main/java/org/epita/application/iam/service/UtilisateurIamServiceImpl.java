package org.epita.application.iam.service;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurIamServiceImpl implements UtilisateurIamService {
    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity utilisateurIamApiDto) {
        return null;
    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIamEntreeApiDto) {
        return null;
    }

    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam() {
        return null;
    }

    @Override
    public void delUtilisateurIam(String email) {

    }
}
