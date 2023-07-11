package org.epita.application.media;

import org.epita.domaine.media.FilmEntity;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    void creerFilm(FilmEntity filmEntity);

    Optional<FilmEntity> trouverFilmParId(Long id);

    List<FilmEntity> trouverTousLesFilms();

    void supprimerFilmParId(Long id);
}
