package org.epita.application.selection.etiquette;

import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import java.util.List;
import java.util.Optional;

public interface EtiquetteService {
    void creerEtiquette(EtiquetteEntity etiquetteEntity);

    EtiquetteEntity trouverEtiquetteParId(Long id);

    List<EtiquetteEntity> trouverToutesLesEtiquettes();

    void supprimerEtiquetteParId(Long id);

    List<EtiquetteEntity> trouverEtiquetteParUtilisateur(UtilisateurEntity utilisateurEntity);
}
