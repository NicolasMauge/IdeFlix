package org.epita.infrastructure.media;

import org.epita.domaine.media.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
    Optional<FilmEntity> findByIdTmdb(String idTmdb);
}
