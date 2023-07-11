package org.epita.infrastructure.selection;

import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieSelectionneeRepository extends JpaRepository<SerieSelectionneeEntity, Long> {
}
