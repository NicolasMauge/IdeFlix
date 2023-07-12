package org.epita.application.utilisateur.preferences;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import java.util.List;

public interface PreferencesUtilisateurService {
    void creerPreferencesUtilisateur(PreferencesUtilisateurEntity preferencesUtilisateurEntity);

    PreferencesUtilisateurEntity trouverPreferencesUtilisateurParId(Long id);

    List<PreferencesUtilisateurEntity> trouverToutesLesPreferencesUtilisateurs();

    void supprimerPreferencesUtilisateurParId(Long id);
}
