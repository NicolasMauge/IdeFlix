package org.epita.application.media.film;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
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
    public FilmEntity trouverFilmParId(Long id) {
        Optional<FilmEntity> filmEntityOptional = this.filmRepository.findById(id);
        if(filmEntityOptional.isPresent()) {
            return filmEntityOptional.get();
        }
        throw new EntityNotFoundException("Film non trouvé");
    }

    @Override
    public List<FilmEntity> trouverTousLesFilms() {
        return this.filmRepository.findAll();
    }

    @Override
    public void supprimerFilmParId(Long id) {
        this.filmRepository.deleteById(id);
    }

    @Override
    public FilmEntity trouverFilmParIdTmdb(String idTmdb) {
        Optional<FilmEntity> filmEntityOptional = this.filmRepository.findByIdTmdb(idTmdb);
        if(filmEntityOptional.isPresent()) {
            return filmEntityOptional.get();
        }
        throw new EntityNotFoundException("Film non trouvé");
    }
}
