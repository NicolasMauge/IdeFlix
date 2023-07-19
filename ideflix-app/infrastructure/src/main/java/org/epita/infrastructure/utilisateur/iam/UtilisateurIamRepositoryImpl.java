package org.epita.infrastructure.utilisateur.iam;

import org.epita.domaine.utilisateuriam.RoleIamEntity;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UtilisateurIamRepositoryImpl implements UtilisateurIamRepository {
    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIam) {

        //List<RoleIamEntity> listeRoleIamEntity = new RoleIamEntity[].{new RoleIamEntity("ROLE_UTILISATEUR")};


        return new UtilisateurIamEntity("BOUCHON", "Dave", "dave.bouchon@mock.fr", "mdpEnClair");
    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIam) {
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
