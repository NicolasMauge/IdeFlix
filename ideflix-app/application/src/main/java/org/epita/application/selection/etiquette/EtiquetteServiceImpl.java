package org.epita.application.selection.etiquette;

import org.epita.application.media.film.FilmServiceImpl;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.selection.EtiquetteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetteServiceImpl implements EtiquetteService {
    public static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    EtiquetteRepository etiquetteRepository;

    public EtiquetteServiceImpl(EtiquetteRepository etiquetteRepository) {
        this.etiquetteRepository = etiquetteRepository;
    }

    @Override
    public void creerEtiquette(EtiquetteEntity etiquette) {
        logger.debug("IdeFlix - creerEtiquette - tentative de création de l'étiquette : {id : " + etiquette.getId() + ", nom étiquette : " + etiquette.getNomTag() + ", email utilisateur : " + etiquette.getUtilisateurEntity().getEmail() + "}");
        this.etiquetteRepository.save(etiquette);
    }

    @Override
    public EtiquetteEntity trouverEtiquetteParId(Long id) {
        Optional<EtiquetteEntity> etiquetteEntityOptional = this.etiquetteRepository.findById(id);
        if (etiquetteEntityOptional.isPresent()) {
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
    public void supprimerEtiquettesParEmail(String email) {
        this.etiquetteRepository.deleteByUtilisateurEntity_Email(email);
    }

    @Override
    public List<EtiquetteEntity> trouverEtiquetteParUtilisateur(UtilisateurEntity utilisateurEntity) {
        return this.etiquetteRepository.findByUtilisateurEntity(utilisateurEntity);
    }

    @Override
    public List<EtiquetteEntity> trouverEtiquettesParEmailUtilisateur(String email) {
        return this.etiquetteRepository.findByUtilisateurEntity_Email(email);
    }
}
