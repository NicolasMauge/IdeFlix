package org.epita.infrastructure.mediaDataBase;

import org.epita.domaine.mediaDataBase.GenreDataBase;

import java.util.List;

public interface GenreDataBaseRepository {

    List<GenreDataBase> searchAllGenresForTV();

    List<GenreDataBase> searchAllGenresForMovie();

}
