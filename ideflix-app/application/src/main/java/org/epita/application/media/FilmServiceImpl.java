package org.epita.application.media;

import org.epita.domaine.media.Film;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Override
    public void creerFilm(Film film) {

    }

    @Override
    public Film trouverFilmParId(Long id) {
        return null;
    }

    @Override
    public List<Film> trouverTousLesFilms() {
        return null;
    }

    @Override
    public void supprimerFilm(Film film) {

    }
}
