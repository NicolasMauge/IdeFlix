package org.epita.application.mediaDataBase.movieDataBase;

import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.infrastructure.mediaDataBase.MovieDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDataBaseServiceImpl implements MovieDataBaseService {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseServiceImpl.class);

    MovieDataBaseRepository movieDataBaseRepository;

    public MovieDataBaseServiceImpl(MovieDataBaseRepository movieTmdbRepository) {
        this.movieDataBaseRepository = movieTmdbRepository;
    }

    @Override
    public List<MovieDataBase> searchMovies(String query) {

//        logger.debug("recherche liste films selon " + query);
        return movieDataBaseRepository.searchMovieDataBase(query);
    }

    @Override
    public MovieDataBase findMovieById(long Id) {
//        logger.debug("recherche détail d'un films selon id" + Id);
        return movieDataBaseRepository.findDetailMovieDataBase(Id);
    }
}
