package org.epita.application.selection;

import org.epita.domaine.selection.EtiquetteEntity;

import java.util.List;
import java.util.Optional;

public interface EtiquetteService {
    void creerEtiquette(EtiquetteEntity etiquetteEntity);

    Optional<EtiquetteEntity> trouverEtiquetteParId(Long id);

    List<EtiquetteEntity> trouverToutesLesEtiquettes();

    void supprimerEtiquetteParId(Long id);
}
