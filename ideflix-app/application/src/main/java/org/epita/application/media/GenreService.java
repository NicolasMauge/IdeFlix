package org.epita.application.media;

import org.epita.domaine.media.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    void creerGenre(GenreEntity genreEntity);

    GenreEntity trouverGenreParId(Long id);

    List<GenreEntity> trouverTousLesGenres();

    void supprimerGenreParId(Long id);
}
