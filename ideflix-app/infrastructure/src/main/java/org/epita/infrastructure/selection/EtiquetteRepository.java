package org.epita.infrastructure.selection;

import org.epita.domaine.selection.EtiquetteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetteRepository extends JpaRepository<EtiquetteEntity, Long> {
}
