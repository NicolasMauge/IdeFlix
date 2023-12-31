package org.epita.infrastructure.utilisateur.iam;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;

import java.util.List;

public interface UtilisateurIamRepository {

    UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIam);

    UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIam);

    List<UtilisateurIamEntity> getUtilisateursIam(String headerAuthorization);

    void delUtilisateurIam(String headerAuthorization, String email);


    UtilisateurIamEntity initIam();
}
