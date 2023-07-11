package org.epita.application.selection;

import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.selection.EtiquetteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetteServiceImpl implements EtiquetteService {
    EtiquetteRepository etiquetteRepository;

    public EtiquetteServiceImpl(EtiquetteRepository etiquetteRepository) {
        this.etiquetteRepository = etiquetteRepository;
    }

    @Override
    public void creerEtiquette(EtiquetteEntity etiquetteEntity) {
        this.etiquetteRepository.save(etiquetteEntity);
    }

    @Override
    public Optional<EtiquetteEntity> trouverEtiquetteParId(Long id) {
        return this.etiquetteRepository.findById(id);
    }

    @Override
    public List<EtiquetteEntity> trouverToutesLesEtiquettes() {
        return this.etiquetteRepository.findAll();
    }

    @Override
    public void supprimerEtiquetteParId(Long id) {
        this.etiquetteRepository.deleteById(id);
    }

    @Override
    public List<EtiquetteEntity> trouverEtiquetteParUtilisateur(UtilisateurEntity utilisateurEntity) {
        return this.etiquetteRepository.findByUtilisateur(utilisateurEntity);
    }
}
