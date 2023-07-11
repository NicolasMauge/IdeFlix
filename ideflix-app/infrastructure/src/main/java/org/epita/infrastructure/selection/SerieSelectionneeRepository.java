package org.epita.infrastructure.selection;

import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieSelectionneeRepository extends JpaRepository<SerieSelectionneeEntity, Long> {
    List<SerieSelectionneeEntity> findByUtilisateurEntity(UtilisateurEntity utilisateurEntity);
}