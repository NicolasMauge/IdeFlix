package org.epita.application.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.infrastructure.utilisateur.PreferencesUtilisateurRepository;

import java.util.List;
import java.util.Optional;

public class PreferencesUtilisateurServiceImpl implements PreferencesUtilisateurService {
    PreferencesUtilisateurRepository preferencesUtilisateurRepository;

    public PreferencesUtilisateurServiceImpl(PreferencesUtilisateurRepository preferencesUtilisateurRepository) {
        this.preferencesUtilisateurRepository = preferencesUtilisateurRepository;
    }

    @Override
    public void creerPreferencesUtilisateur(PreferencesUtilisateurEntity preferencesUtilisateurEntity) {
        this.preferencesUtilisateurRepository.save(preferencesUtilisateurEntity);
    }

    @Override
    public Optional<PreferencesUtilisateurEntity> trouverPreferencesUtilisateurParId(Long id) {
        return this.preferencesUtilisateurRepository.findById(id);
    }

    @Override
    public List<PreferencesUtilisateurEntity> trouverToutesLesPreferencesUtilisateurs() {
        return this.preferencesUtilisateurRepository.findAll();
    }

    @Override
    public void supprimerPreferencesUtilisateurParId(Long id) {
        this.preferencesUtilisateurRepository.deleteById(id);
    }
}
