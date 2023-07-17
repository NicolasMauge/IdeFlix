package org.epita.application.utilisateur.utilisateur;

import org.epita.domaine.common.EntityNotFoundException;
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
    public UtilisateurEntity trouverUtilisateurParId(Long id) {
        Optional<UtilisateurEntity> utilisateurEntityOptional = this.utilisateurRepository.findById(id);
        if(utilisateurEntityOptional.isPresent()) {
            return utilisateurEntityOptional.get();
        }
        throw new EntityNotFoundException("Utilisateur non trouvé");
    }

    @Override
    public UtilisateurEntity trouverUtilisateurParEmail(String email) {
        Optional<UtilisateurEntity> utilisateurEntityOptional = this.utilisateurRepository.findByEmail(email);
        if(utilisateurEntityOptional.isPresent()) {
            return utilisateurEntityOptional.get();
        }
        throw new EntityNotFoundException("Utilisateur non trouvé");
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
