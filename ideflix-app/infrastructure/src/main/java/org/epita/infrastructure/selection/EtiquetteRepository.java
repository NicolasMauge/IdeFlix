package org.epita.infrastructure.selection;

import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtiquetteRepository extends JpaRepository<EtiquetteEntity, Long> {
    List<EtiquetteEntity> findByUtilisateurEntity(UtilisateurEntity utilisateurEntity);
    List<EtiquetteEntity> findByUtilisateurEntity_Email(String email);
}
