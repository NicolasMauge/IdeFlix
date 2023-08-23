package org.epita.application.media.genre;


import org.epita.application.mediaDataBase.genreDataBase.GenreDataBaseService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.infrastructure.media.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    public static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    private GenreRepository genreRepository;

    private GenreDataBaseService genreDataBaseService;

    public GenreServiceImpl(GenreRepository genreRepository, GenreDataBaseService genreDataBaseService) {
        this.genreRepository = genreRepository;
        this.genreDataBaseService = genreDataBaseService;
    }

    @Override
    public void creerGenre(GenreEntity genreEntity) {
        Optional<GenreEntity> genreTrouve = this.genreRepository.findGenreEntityByIdTmdb(genreEntity.getIdTmdb());
        if(genreTrouve.isPresent()) {
            genreEntity.setId(genreTrouve.get().getId());
            logger.debug("IdeFlix - creerGenre - Genre déjà existant : {id : "+genreEntity.getId()+", idTmdb : " + genreEntity.getIdTmdb() + ", nomGenre : " +genreEntity.getNomGenre()+"}");
        }
        else {
            logger.debug("IdeFlix - creerGenre - Tentative création du genre : {idTmdb : " + genreEntity.getIdTmdb() + ", nomGenre : " +genreEntity.getNomGenre()+"}");
        }

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

    @Override
    public GenreEntity trouverGenreByIdTmdb(String idTmdb) {
        Optional<GenreEntity> genreEntityOptional = this.genreRepository.findGenreEntityByIdTmdb(idTmdb);
        if(genreEntityOptional.isPresent()) {
            return genreEntityOptional.get();
        }
        throw new EntityNotFoundException("Genre non trouvé");
    }

    @Override
    public void loadGenres() {
        // récupérer tous les genres de mediaDataBase
        List<GenreEntity> listGenres = this.genreDataBaseService.searchAllGenresEntity();
        // charger les données
        listGenres
                .stream()
                .forEach(g -> creerGenre(g));
    }
}
