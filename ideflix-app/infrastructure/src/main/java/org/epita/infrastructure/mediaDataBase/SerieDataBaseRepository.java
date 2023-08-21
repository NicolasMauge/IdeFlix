package org.epita.infrastructure.mediaDataBase;

import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;

import java.util.List;

public interface SerieDataBaseRepository {

    List<SerieDataBase> searchSeriesDataBase(String query);

    SerieDataBase findDetailSerieDataBase(long idTmdb);
}
