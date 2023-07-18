package org.epita.application.media.genre;

import org.epita.domaine.media.GenreEntity;

import java.util.List;

public interface GenreService {
    void creerGenre(GenreEntity genreEntity);

    GenreEntity trouverGenreParId(Long id);

    List<GenreEntity> trouverTousLesGenres();

    void supprimerGenreParId(Long id);
}
