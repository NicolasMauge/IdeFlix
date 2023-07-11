package org.epita.application.selection;

import org.epita.domaine.selection.FilmSelectionneEntity;

import java.util.List;
import java.util.Optional;

public interface FilmSelectionneService {
    void creerFilmSelectionne(FilmSelectionneEntity filmSelectionneEntity);

    Optional<FilmSelectionneEntity> trouverFilmSelectionneParId(Long id);

    List<FilmSelectionneEntity> trouverTousLesFilmsSelectionnes();

    void supprimerFilmSelectionneParId(Long id);
}
