package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MediaDataBaseException;
import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.EpisodeResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.EpisodeApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;


@Repository
public class EpisodeDataBaseRepositoryImpl implements EpisodeDataBaseRepository {

    private static final Logger logger = LoggerFactory.getLogger(EpisodeDataBaseRepositoryImpl.class);
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String LANGUAGE = "fr-FR";
    private final TmdbConfig tmdbConfig;

    private final EpisodeApiMapper episodeApiMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public EpisodeDataBaseRepositoryImpl(TmdbConfig tmdbConfig, EpisodeApiMapper episodeApiMapper) {
        this.tmdbConfig = tmdbConfig;
        this.episodeApiMapper = episodeApiMapper;
    }

    @Override
    public EpisodeSerieDataBase searchEpisodeByIdSeriesAndNumberSeasonAndEpisodeNumber(long id, int numberSeason, int numberEpisode) {

//        https://api.themoviedb.org/3/tv/1408/season/8/episode/1?language=fr-FR'

        String url = BASE_URL + "tv/" + id + "/season/" + numberSeason + "/episode/" + numberEpisode + "?&api_key=" + tmdbConfig.getTmdbApiKey() +  "&language=" + LANGUAGE;

        System.out.println(url);

        logger.debug("recherche détail de l'épisode " + numberEpisode + " de la saison " + numberSeason + " de la série d'id " + id);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
//                System.out.println("retour: " + jsonResponse);
                EpisodeResponseDto episodeResponseDto = objectMapper.readValue(jsonResponse, EpisodeResponseDto.class);
                return episodeApiMapper.mapEpisodeResponseDtoToEntity(episodeResponseDto);

            } else {
                throw new MediaDataBaseException("APP - Tmdb - Echec recherche du détail d'un  épisode " +  numberEpisode + "," +
                        " saison " + numberSeason + " et id série :  " + id + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
