package org.epita.ideflixiam.application.utilisateuriam;

import org.epita.ideflixiam.domaine.UtilisateurIamEntity;

import java.util.List;

public interface UtilisateurIamService {

    UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIamEntity);

    UtilisateurIamEntity recupererUtilisateurIamParEmail(String email);


    List<UtilisateurIamEntity> recupererTousUtilisateurIam();


    UtilisateurIamEntity mettreAJourUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity);


    void supprimerUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity);
}
