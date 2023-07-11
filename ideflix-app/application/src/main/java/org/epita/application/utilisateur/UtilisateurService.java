package org.epita.application.utilisateur;

import org.epita.domaine.utilisateur.UtilisateurEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    void creerUtilisateur(UtilisateurEntity utilisateurEntity);

    Optional<UtilisateurEntity> trouverUtilisateurParId(Long id);

    List<UtilisateurEntity> trouverTousLesUtilisateurs();

    void supprimerUtilisateurParId(Long id);
}
