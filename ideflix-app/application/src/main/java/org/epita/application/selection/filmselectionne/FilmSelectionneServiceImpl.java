package org.epita.application.selection.filmselectionne;

import org.epita.application.media.film.FilmService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.media.FilmRepository;
import org.epita.infrastructure.selection.FilmSelectionneRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class FilmSelectionneServiceImpl implements FilmSelectionneService {
    FilmSelectionneRepository filmSelectionneRepository;
    FilmRepository filmRepository;
    FilmService filmService;

    public FilmSelectionneServiceImpl(FilmSelectionneRepository filmSelectionneRepository, FilmRepository filmRepository, FilmService filmService) {
        this.filmSelectionneRepository = filmSelectionneRepository;
        this.filmRepository = filmRepository;
        this.filmService = filmService;
    }

    @Override
    public void creerFilmSelectionne(FilmSelectionneEntity filmSelectionneEntity) {
        String idTmdb = filmSelectionneEntity.getMediaAudioVisuelEntity().getIdTmdb();
        String email = filmSelectionneEntity.getUtilisateurEntity().getEmail();

        Optional<FilmEntity> film =
                this.filmRepository.findByIdTmdb(idTmdb);
        if(film.isPresent()) {
            film.get().setId(filmSelectionneEntity.getMediaAudioVisuelEntity().getId());
        } /*else {
            this.filmService.creerFilm((FilmEntity) filmSelectionneEntity.getMediaAudioVisuelEntity());
        } */

        Optional<FilmSelectionneEntity> filmSelectionneEntityOptional =
                //this.filmSelectionneRepository.findFilmSelectionneEntityByMediaAudioVisuelEntity_IdTmdb(idTmdb);
                this.filmSelectionneRepository.findFilmSelectionneEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(email, idTmdb);
        if(filmSelectionneEntityOptional.isPresent()) {
            filmSelectionneEntityOptional.get().setId(filmSelectionneEntity.getId());
        }

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
