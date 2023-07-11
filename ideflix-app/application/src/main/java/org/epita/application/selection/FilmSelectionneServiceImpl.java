package org.epita.application.selection;

import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.infrastructure.selection.FilmSelectionneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmSelectionneServiceImpl implements FilmSelectionneService {
    FilmSelectionneRepository filmSelectionneRepository;

    public FilmSelectionneServiceImpl(FilmSelectionneRepository filmSelectionneRepository) {
        this.filmSelectionneRepository = filmSelectionneRepository;
    }

    @Override
    public void creerFilmSelectionne(FilmSelectionneEntity filmSelectionneEntity) {
        this.filmSelectionneRepository.save(filmSelectionneEntity);
    }

    @Override
    public Optional<FilmSelectionneEntity> trouverFilmSelectionneParId(Long id) {
        return this.filmSelectionneRepository.findById(id);
    }

    @Override
    public List<FilmSelectionneEntity> trouverTousLesFilmsSelectionnes() {
        return this.filmSelectionneRepository.findAll();
    }

    @Override
    public void supprimerFilmSelectionneParId(Long id) {
        this.filmSelectionneRepository.deleteById(id);
    }
}
