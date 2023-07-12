package org.epita.application.media;

import org.epita.domaine.common.EntityNotFoundException;
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
    public GenreEntity trouverGenreParId(Long id) {
        Optional<GenreEntity> genreEntityOptional = this.genreRepository.findById(id);
        if(genreEntityOptional.isPresent()) {
            return genreEntityOptional.get();
        }
        throw new EntityNotFoundException("Genre non trouvé");
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
