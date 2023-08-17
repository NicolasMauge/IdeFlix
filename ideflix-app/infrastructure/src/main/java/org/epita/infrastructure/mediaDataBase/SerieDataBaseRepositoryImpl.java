package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MovieDataBaseException;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.SearchMoviesResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchSeriesResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.MovieApiMapper;
import org.epita.infrastructure.mediaDataBase.mapper.SerieApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class SerieDataBaseRepositoryImpl implements SerieDataBaseRepository{

    private static final Logger logger = LoggerFactory.getLogger(SerieDataBaseRepositoryImpl.class);
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final boolean INCLUDE_ADULT = false;
    private static final String LANGUAGE = "fr-FR";
    private TmdbConfig tmdbConfig;

    private SerieApiMapper serieApiMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SerieDataBaseRepositoryImpl(TmdbConfig tmdbConfig, SerieApiMapper serieApiMapper) {
        this.tmdbConfig = tmdbConfig;
        this.serieApiMapper = serieApiMapper;
    }

    @Override
    public List<SerieDataBase> searchSeriesDataBase(String query) {
        String url = BASE_URL + "search/tv?query=" + query + "&api_key=" + tmdbConfig.getTmdbApiKey() + "&include_adult=" + INCLUDE_ADULT +  "&language=" + LANGUAGE;

//        System.out.println(url);

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
                SearchSeriesResponseDto searchSeriesResponseDto = objectMapper.readValue(jsonResponse, SearchSeriesResponseDto.class);
                List<SerieDataBase> listSerieDataBase = serieApiMapper.mapSearchSeriesResponseDtoToEntityList(searchSeriesResponseDto);
                return listSerieDataBase;

            } else {
                throw new MovieDataBaseException("APP - Tmdb - Echec recherche liste de séries avec caractères:  " +  query + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
