package org.epita.application.media;

import org.epita.domaine.media.Film;

import java.util.List;

public interface FilmService {
    void creerFilm(Film film);

    Film trouverFilmParId(Long id);

    List<Film> trouverTousLesFilms();

    void supprimerFilm(Film film);
}
