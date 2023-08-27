package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.domaine.mediaDataBase.SerieDataBase;

import java.util.List;

public interface SerieDataBaseService {

    List<SerieDataBase> searchSeries(String query);

    SerieDataBase findSerieById(long Id);

    List<SerieDataBase> searchSuggestedSeries(int page);

    List<SerieDataBase> searchSuggestedSeriesSelonPreferences(String email, int page);
}
