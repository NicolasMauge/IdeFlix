package org.epita.application.selection.filmselectionne;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
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
    public FilmSelectionneEntity trouverFilmSelectionneParId(Long id) {
        Optional<FilmSelectionneEntity> filmSelectionneEntityOptional = this.filmSelectionneRepository.findById(id);
        if(filmSelectionneEntityOptional.isPresent()) {
            return filmSelectionneEntityOptional.get();
        }
        throw new EntityNotFoundException("Film sélectionné non trouvé");
    }

    @Override
    public List<FilmSelectionneEntity> trouverTousLesFilmsSelectionnes() {
        return this.filmSelectionneRepository.findAll();
    }

    @Override
    public void supprimerFilmSelectionneParId(Long id) {
        this.filmSelectionneRepository.deleteById(id);
    }

    @Override
    public List<FilmSelectionneEntity> trouverFilmSelectionneeParUtilisateur(UtilisateurEntity utilisateurEntity) {
        return this.filmSelectionneRepository.findByUtilisateurEntity(utilisateurEntity);
    }

    @Override
    public List<FilmSelectionneEntity> trouverFilmsSelectionnesParEmailUtilisateur(String email) {
        return this.filmSelectionneRepository.findFilmSelectionneEntitiesByUtilisateurEntity_EmailIs(email);
    }
}
