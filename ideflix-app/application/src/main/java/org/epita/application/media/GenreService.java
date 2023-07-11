package org.epita.application.media;

import org.epita.domaine.media.Genre;

import java.util.List;

public interface GenreService {
    void creerGenre(Genre genre);

    Genre trouverGenreParId(Long id);

    List<Genre> trouverTousLesGenres();

    void supprimerGenre(Genre genre);
}
