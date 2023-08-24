package org.epita.infrastructure.mediaDataBase;

import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;

public interface EpisodeDataBaseRepository {

    EpisodeSerieDataBase searchEpisodeByIdSeriesAndNumberSeasonAndEpisodeNumber(long id,
                                                                                int numberSeason,
                                                                                int numberEpisode);
}
