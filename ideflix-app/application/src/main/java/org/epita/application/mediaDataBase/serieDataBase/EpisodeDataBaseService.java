package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;

public interface EpisodeDataBaseService {

    EpisodeSerieDataBase findEpisodeByNumberEpisodeAndNumberSeasonAndId(long id, int numberSeason, int numberEpisode);
}
