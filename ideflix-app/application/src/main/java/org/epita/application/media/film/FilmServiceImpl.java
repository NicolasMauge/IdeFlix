package org.epita.application.media.film;

import org.epita.application.media.genre.GenreService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.common.IamUtilisateurInterditException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.infrastructure.media.FilmRepository;
import org.epita.infrastructure.media.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {
    public static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    private FilmRepository filmRepository;
    private GenreService genreService;
    private GenreRepository genreRepository;

    public FilmServiceImpl(FilmRepository filmRepository, GenreService genreService, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreService = genreService;
        this.genreRepository = genreRepository;
    }

    @Override
    public void creerFilm(FilmEntity filmEntity) {
        // pour tous les genres, on voit pour les créer
        filmEntity.getGenreList().forEach(genre-> {
            Optional<GenreEntity> genreTrouve = this.genreRepository.findGenreEntityByIdTmdb(genre.getIdTmdb());
            if(genreTrouve.isPresent()) {
                genre.setId(genreTrouve.get().getId());
            }
            else {
                logger.debug("IdeFlix - creerFilm - Tentative création du genre : {idTmdb : " + genre.getIdTmdb() + ", nomGenre : " +genre.getNomGenre()+"}");
            }
        });

        // on vérifie si le film existe déjà en base, et si oui, on ajoute son id trouvé
        Optional<FilmEntity> filmTrouve = this.filmRepository.findByIdTmdb(filmEntity.getIdTmdb());
        if(filmTrouve.isPresent()) {
            filmEntity.setId(filmTrouve.get().getId());
            logger.debug("IdeFlix - creerFilm : film trouvé avec l'idTmdb = " + filmEntity.getIdTmdb());
        }

        logger.debug("IdeFlix - creerFilm : tentative de création avec l'idTmdb = " + filmEntity.getIdTmdb());

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
