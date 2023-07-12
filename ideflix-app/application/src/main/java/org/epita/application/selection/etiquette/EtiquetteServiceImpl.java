package org.epita.application.selection.etiquette;

import org.epita.application.selection.etiquette.EtiquetteService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.SerieEntity;
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
    public EtiquetteEntity trouverEtiquetteParId(Long id) {
        Optional<EtiquetteEntity> etiquetteEntityOptional = this.etiquetteRepository.findById(id);
        if(etiquetteEntityOptional.isPresent()) {
            return etiquetteEntityOptional.get();
        }
        throw new EntityNotFoundException("Etiquette non trouvée");
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
        return this.etiquetteRepository.findByUtilisateurEntity(utilisateurEntity);
    }
}
