package org.epita.infrastructure.selection;

import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmSelectionneRepository extends JpaRepository<FilmSelectionneEntity, Long> {
    List<FilmSelectionneEntity> findByUtilisateurEntity(UtilisateurEntity utilisateurEntity);

    List<FilmSelectionneEntity> findFilmSelectionneEntitiesByUtilisateurEntity_EmailIs(String email);
}
