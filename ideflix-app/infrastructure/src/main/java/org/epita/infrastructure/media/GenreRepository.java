package org.epita.infrastructure.media;

import org.epita.domaine.media.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    Optional<GenreEntity> findGenreEntityByIdTmdb(String idTmdb);
}
