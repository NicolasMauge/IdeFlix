package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.domaine.mediaDataBase.SerieDataBase;

import java.util.List;

public interface SerieDataBaseService {

    List<SerieDataBase> searchSeries(String query);

    SerieDataBase findSerieById(long Id);
}
