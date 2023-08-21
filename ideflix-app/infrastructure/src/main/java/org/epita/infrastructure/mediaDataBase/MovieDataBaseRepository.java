package org.epita.infrastructure.mediaDataBase;

import org.epita.domaine.mediaDataBase.MovieDataBase;

import java.util.List;


public interface MovieDataBaseRepository {

    List<MovieDataBase> searchAllMovieDataBaseWithQuery(String query);

    MovieDataBase findDetailMovieDataBase(long idTmdb);

    List<MovieDataBase> searchSuggestedMovieDatabase(int page);
}
