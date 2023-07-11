package org.epita.application.utilisateur;

import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.utilisateur.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void creerUtilisateur(UtilisateurEntity utilisateurEntity) {
        this.utilisateurRepository.save(utilisateurEntity);
    }

    @Override
    public Optional<UtilisateurEntity> trouverUtilisateurParId(Long id) {
        return this.utilisateurRepository.findById(id);
    }

    @Override
    public List<UtilisateurEntity> trouverTousLesUtilisateurs() {
        return this.utilisateurRepository.findAll();
    }

    @Override
    public void supprimerUtilisateurParId(Long id) {
        this.utilisateurRepository.deleteById(id);
    }
}
