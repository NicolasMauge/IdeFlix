package org.epita.application.iam.service;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;

import java.util.List;

public interface UtilisateurIamService {

    UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity utilisateurIamApiDto);

    UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIamEntreeApiDto);

    List<UtilisateurIamEntity> getUtilisateursIam(String headerAuthorization);

    void delUtilisateurIam(String headerAuthorization, String email);

    public void initIam();

}
