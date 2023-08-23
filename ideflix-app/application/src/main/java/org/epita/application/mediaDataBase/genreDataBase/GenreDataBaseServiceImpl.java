package org.epita.application.mediaDataBase.genreDataBase;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.infrastructure.mediaDataBase.GenreDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GenreDataBaseServiceImpl implements GenreDataBaseService {

    private static final Logger logger = LoggerFactory.getLogger(GenreDataBaseServiceImpl.class);

    GenreDataBaseRepository genreDataBaseRepository;

    public GenreDataBaseServiceImpl(GenreDataBaseRepository genreDataBaseRepository) {
        this.genreDataBaseRepository = genreDataBaseRepository;
    }

    @Override
    public List<GenreDataBase> searchAllGenres() {
        logger.debug("recherche de la liste des genres TV et Movie dans référentiel");

        List<GenreDataBase> tvGenres = genreDataBaseRepository.searchAllGenresForTV();
        List<GenreDataBase> movieGenres = genreDataBaseRepository.searchAllGenresForMovie();

        List<GenreDataBase> mergedGenres =
                // fusion des 2 listes
                Stream.concat(tvGenres.stream(), movieGenres.stream())
                // collecter les genres dans une Map où la clé est l'Id du genre et la valeur est l'objet GenreDataBase
                // et fonction de fusion pour garantir que si un genre a le même Id, seule la 1ère occurrence est conservée
                .collect(Collectors.toMap(GenreDataBase::getIdDatabase, Function.identity(), (existing, replacement) -> existing))
                // extraction des valeurs
                .values()
                // tri des genres par Id et collect dans une liste
                .stream()
                .sorted(Comparator.comparing(GenreDataBase::getNomGenre))
                .collect(Collectors.toList());

        return mergedGenres;
    }
}
