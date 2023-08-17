package org.epita.application.mediaDataBase.movieDataBase;

import org.epita.domaine.mediaDataBase.MovieDataBase;

import java.util.List;


public interface MovieDataBaseService {

    List<MovieDataBase> searchMovies(String query);

    MovieDataBase findMovieById(long Id);

}
