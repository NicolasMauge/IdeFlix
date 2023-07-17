package org.epita.application.media.film;

import org.epita.domaine.media.FilmEntity;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    void creerFilm(FilmEntity filmEntity);

    FilmEntity trouverFilmParId(Long id);

    List<FilmEntity> trouverTousLesFilms();

    void supprimerFilmParId(Long id);

    FilmEntity trouverFilmParIdTmdb(String idTmdb);
}
