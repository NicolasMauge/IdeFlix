package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.infrastructure.mediaDataBase.EpisodeDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EpisodeDataBaseServiceImpl implements EpisodeDataBaseService {

    private static final Logger logger = LoggerFactory.getLogger(EpisodeDataBaseServiceImpl.class);

    EpisodeDataBaseRepository episodeDataBaseRepository;

    public EpisodeDataBaseServiceImpl(EpisodeDataBaseRepository episodeDataBaseRepository) {
        this.episodeDataBaseRepository = episodeDataBaseRepository;
    }

    @Override
    public EpisodeSerieDataBase findEpisodeByNumberEpisodeAndNumberSeasonAndId(long id, int numberSeason, int numberEpisode) {
        logger.debug("application - recherche d'un épisode de la série d'id: " + id + " épisode: " + numberEpisode + " saison: "  + numberSeason);
        return this.episodeDataBaseRepository.searchEpisodeByIdSeriesAndNumberSeasonAndEpisodeNumber(id, numberSeason, numberEpisode);
    }
}


