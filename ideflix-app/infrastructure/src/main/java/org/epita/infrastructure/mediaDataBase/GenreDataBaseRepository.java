package org.epita.infrastructure.mediaDataBase;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;

import java.util.List;

public interface GenreDataBaseRepository {

    List<GenreDataBase> searchAllGenresForTV();

    List<GenreDataBase> searchAllGenresForMovie();

    List<GenreEntity> searchAllGenresEntityForTV();

    List<GenreEntity> searchAllGenresEntityForMovie();

}
