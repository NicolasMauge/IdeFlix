package org.epita.application.media;

import org.epita.domaine.media.GenreEntity;
import org.epita.infrastructure.media.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void creerGenre(GenreEntity genreEntity) {
        this.genreRepository.save(genreEntity);
    }

    @Override
    public Optional<GenreEntity> trouverGenreParId(Long id) {
        return this.genreRepository.findById(id);
    }

    @Override
    public List<GenreEntity> trouverTousLesGenres() {
        return this.genreRepository.findAll();
    }

    @Override
    public void supprimerGenreParId(Long id) {
        this.genreRepository.deleteById(id);
    }
}
