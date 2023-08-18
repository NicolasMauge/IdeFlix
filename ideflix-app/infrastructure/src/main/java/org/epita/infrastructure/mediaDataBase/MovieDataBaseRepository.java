package org.epita.infrastructure.mediaDataBase;

import org.epita.domaine.mediaDataBase.MovieDataBase;

import java.util.List;


public interface MovieDataBaseRepository {

    List<MovieDataBase> searchMovieDataBase(String query);

    MovieDataBase findDetailMovieDataBase(long idTmdb);
}
