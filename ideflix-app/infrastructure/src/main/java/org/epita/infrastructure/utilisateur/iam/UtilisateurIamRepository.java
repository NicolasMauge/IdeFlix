package org.epita.infrastructure.utilisateur.iam;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;

import java.util.List;

public interface UtilisateurIamRepository {

    UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIam);

    UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIam);

    List<UtilisateurIamEntity> getUtilisateursIam();

    void delUtilisateurIam(String email);


}
