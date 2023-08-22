package org.epita.application.media.serie;

import org.epita.application.media.genre.GenreServiceImpl;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.infrastructure.media.GenreRepository;
import org.epita.infrastructure.media.SerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {
    public static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    private SerieRepository serieRepository;
    private GenreRepository genreRepository;

    public SerieServiceImpl(SerieRepository serieRepository, GenreRepository genreRepository) {
        this.serieRepository = serieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void creerSerie(SerieEntity serieEntity) {
        // compléter les id des genres
        serieEntity.getGenreList().forEach(genre-> {
            Optional<GenreEntity> genreTrouve = this.genreRepository.findGenreEntityByIdTmdb(genre.getIdTmdb());
            if(genreTrouve.isPresent()) {
                genre.setId(genreTrouve.get().getId());
                logger.debug("IdeFlix - creerSerie - Genre déjà existant : {id : "+genre.getId()+", idTmdb : " + genre.getIdTmdb() + ", nomGenre : " +genre.getNomGenre()+"}");
            }
            else {
                logger.debug("IdeFlix - creerSerie - Problème : un genre n'existe pas, il faut d'abord le créer");
            }
        });

        // on vérifie si le film existe déjà en base, et si oui, on ajoute son id trouvé
        Optional<SerieEntity> serieTrouve = this.serieRepository.findByIdTmdb(serieEntity.getIdTmdb());
        if(serieTrouve.isPresent()) {
            serieEntity.setId(serieTrouve.get().getId());
            logger.debug("IdeFlix - creerSerie : série trouvée avec l'idTmdb = " + serieEntity.getIdTmdb());
        }

        logger.debug("IdeFlix - creerSerie : tentative de création avec l'idTmdb = " + serieEntity.getIdTmdb());

        this.serieRepository.save(serieEntity);
    }

    @Override
    public SerieEntity trouverSerieParId(Long id) {
        Optional<SerieEntity> serieEntityOptional = this.serieRepository.findById(id);
        if(serieEntityOptional.isPresent()) {
            return serieEntityOptional.get();
        }
        throw new EntityNotFoundException("Série non trouvée");
    }

    @Override
    public List<SerieEntity> trouverToutesLesSeries() {
        return this.serieRepository.findAll();
    }

    @Override
    public void supprimerSerieParId(Long id) {
        this.serieRepository.deleteById(id);
    }

    @Override
    public SerieEntity trouverSerieParIdTmdb(String idTmdb) {
        Optional<SerieEntity> serieEntityOptional = this.serieRepository.findByIdTmdb(idTmdb);
        if(serieEntityOptional.isPresent()) {
            return serieEntityOptional.get();
        }
        throw new EntityNotFoundException("Série non trouvée");
    }
}
