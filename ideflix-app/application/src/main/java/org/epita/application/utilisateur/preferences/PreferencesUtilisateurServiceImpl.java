package org.epita.application.utilisateur.preferences;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.infrastructure.utilisateur.PreferencesUtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public PreferencesUtilisateurEntity trouverPreferencesUtilisateurParId(Long id) {
        Optional<PreferencesUtilisateurEntity> preferencesUtilisateurEntityOptional = this.preferencesUtilisateurRepository.findById(id);
        if(preferencesUtilisateurEntityOptional.isPresent()) {
            return preferencesUtilisateurEntityOptional.get();
        }
        throw new EntityNotFoundException("Préférence utilisateur non trouvée");
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
