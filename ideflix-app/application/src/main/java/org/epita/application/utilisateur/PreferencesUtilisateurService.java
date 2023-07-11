package org.epita.application.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import java.util.List;
import java.util.Optional;

public interface PreferencesUtilisateurService {
    void creerPreferencesUtilisateur(PreferencesUtilisateurEntity preferencesUtilisateurEntity);

    Optional<PreferencesUtilisateurEntity> trouverPreferencesUtilisateurParId(Long id);

    List<PreferencesUtilisateurEntity> trouverToutesLesPreferencesUtilisateurs();

    void supprimerPreferencesUtilisateurParId(Long id);
}
