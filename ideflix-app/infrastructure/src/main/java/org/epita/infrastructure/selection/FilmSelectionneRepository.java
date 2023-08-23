package org.epita.infrastructure.selection;

import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmSelectionneRepository extends JpaRepository<FilmSelectionneEntity, Long> {
    List<FilmSelectionneEntity> findByUtilisateurEntity(UtilisateurEntity utilisateurEntity);

    List<FilmSelectionneEntity> findFilmSelectionneEntitiesByUtilisateurEntity_EmailIs(String email);

    Optional<FilmSelectionneEntity> findFilmSelectionneEntityByMediaAudioVisuelEntity_IdTmdb(String idTmdb);

    Optional<FilmSelectionneEntity> findFilmSelectionneEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(String email, String idTmdb);

    void deleteByUtilisateurEntity_Email(String email);
}
