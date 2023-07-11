package org.epita.application.media;

import org.epita.domaine.media.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Override
    public void creerGenre(Genre genre) {

    }

    @Override
    public Genre trouverGenreParId(Long id) {
        return null;
    }

    @Override
    public List<Genre> trouverTousLesGenres() {
        return null;
    }

    @Override
    public void supprimerGenre(Genre genre) {

    }
}
