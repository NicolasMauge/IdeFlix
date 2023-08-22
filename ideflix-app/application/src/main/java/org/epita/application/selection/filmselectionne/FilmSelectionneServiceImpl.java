package org.epita.application.selection.filmselectionne;

import org.epita.application.media.film.FilmService;
import org.epita.application.media.film.FilmServiceImpl;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.media.FilmRepository;
import org.epita.infrastructure.selection.FilmSelectionneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmSelectionneServiceImpl implements FilmSelectionneService {
    public static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    FilmSelectionneRepository filmSelectionneRepository;
    FilmRepository filmRepository;
    FilmService filmService;

    public FilmSelectionneServiceImpl(FilmSelectionneRepository filmSelectionneRepository, FilmRepository filmRepository, FilmService filmService) {
        this.filmSelectionneRepository = filmSelectionneRepository;
        this.filmRepository = filmRepository;
        this.filmService = filmService;
    }

    @Override
    public void creerFilmSelectionne(FilmSelectionneEntity filmSelectionne) {
        String idTmdb = filmSelectionne.getMediaAudioVisuelEntity().getIdTmdb();
        String email = filmSelectionne.getUtilisateurEntity().getEmail();

        Optional<FilmEntity> film =
                this.filmRepository.findByIdTmdb(idTmdb);
        if(film.isPresent()) {
            filmSelectionne.getMediaAudioVisuelEntity().setId(film.get().getId());
            logger.debug("IdeFlix - creerFilmSelectionne - film déjà existant : {idTmdb : "+ filmSelectionne.getMediaAudioVisuelEntity().getIdTmdb()+"}");
        }

        Optional<FilmSelectionneEntity> filmSelectionneEntityOptional =
                this.filmSelectionneRepository.findFilmSelectionneEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(email, idTmdb);
        if(filmSelectionneEntityOptional.isPresent()) {
            filmSelectionne.setId(filmSelectionneEntityOptional.get().getId());
            logger.debug("IdeFlix - creerFilmSelectionne - filmSelectionne déjà existant");
        }

        logger.debug("IdeFlix - creerFilmSelectionne - détails : {id : "+filmSelectionne.getId()
                +", titre du média : " + filmSelectionne.getMediaAudioVisuelEntity().getTitre()
                +", id du média :" + filmSelectionne.getMediaAudioVisuelEntity().getId()
                + ", idTmdb : " +filmSelectionne.getMediaAudioVisuelEntity().getIdTmdb()+"}");

        this.filmSelectionneRepository.save(filmSelectionne);
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

    @Override
    public List<FilmSelectionneEntity> trouverFilmSelectionnesParEmailUtilisateurEtIdTmdb(String email, String idTmdb) {
        List<FilmSelectionneEntity> filmList = new ArrayList<>();

        Optional<FilmSelectionneEntity> filmOpt = this.filmSelectionneRepository.findFilmSelectionneEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(email, idTmdb);
        if(filmOpt.isPresent()) {
            filmList.add(filmOpt.get());
        }

        return filmList;
    }
}
