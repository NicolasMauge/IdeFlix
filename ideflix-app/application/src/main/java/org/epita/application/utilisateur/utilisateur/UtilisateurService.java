package org.epita.application.utilisateur.utilisateur;

import org.epita.domaine.utilisateur.UtilisateurEntity;
import java.util.List;

public interface UtilisateurService {
    void creerUtilisateur(UtilisateurEntity utilisateurEntity);

    UtilisateurEntity trouverUtilisateurParId(Long id);

    UtilisateurEntity trouverUtilisateurParEmail(String email);

    List<UtilisateurEntity> trouverTousLesUtilisateurs();

    void supprimerUtilisateurParId(Long id);
}
