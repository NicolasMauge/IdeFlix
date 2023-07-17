package org.epita.infrastructure.media;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SerieRepository extends JpaRepository<SerieEntity, Long> {
    Optional<SerieEntity> findByIdTmdb(String idTmdb);
}
