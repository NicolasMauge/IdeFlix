package org.epita.application.media;

import org.epita.domaine.media.FilmEntity;
import org.epita.infrastructure.media.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public void creerFilm(FilmEntity filmEntity) {
        this.filmRepository.save(filmEntity);
    }

    @Override
    public Optional<FilmEntity> trouverFilmParId(Long id) {
        return this.filmRepository.findById(id);
    }

    @Override
    public List<FilmEntity> trouverTousLesFilms() {
        return this.filmRepository.findAll();
    }

    @Override
    public void supprimerFilmParId(Long id) {
        this.filmRepository.deleteById(id);
    }
}
