package org.epita.application.mediaDataBase.mediaDataBase;

import org.epita.application.mediaDataBase.movieDataBase.MovieDataBaseService;
import org.epita.application.mediaDataBase.serieDataBase.SerieDataBaseService;
import org.epita.domaine.mediaDataBase.MediaAudioVisuelDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.MovieDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaDataBaseServiceImpl implements MediaDataBaseService {

    private static final Logger logger = LoggerFactory.getLogger(MediaDataBaseServiceImpl.class);

    MovieDataBaseService movieDataBaseService;

    SerieDataBaseService serieDataBaseService;

    @Override
    public List<MediaAudioVisuelDataBase> searchSuggestedMediasSelonPreferences(String email, int page) {

        logger.debug("recherche de la liste dessuggestions films + séries dans référentiel Database");

        List<MovieDataBase> movieDataBaseList = movieDataBaseService.searchSuggestedMoviesSelonPreferences(email, page);
        List<SerieDataBase> serieDataBaseList = serieDataBaseService.searchSuggestedSeriesSelonPreferences(email, page);

        return null;
    }
}
