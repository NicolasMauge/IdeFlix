package org.epita.application.mediaDataBase.genreDataBase;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;

import java.util.List;

public interface GenreDataBaseService {

    List<GenreDataBase> searchAllGenres();

    List<GenreEntity> searchAllGenresEntity();
}
