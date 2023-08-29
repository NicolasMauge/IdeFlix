package org.epita.infrastructure.mediaDataBase;


import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MediaDataBaseException;
import org.epita.domaine.common.MediaDataBaseNonTrouveException;
import org.epita.domaine.common.MediaDataBaseUnAuthorizedAccessException;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.DetailSerieResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchSeriesResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.SerieApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SerieDataBaseRepositoryImpl implements SerieDataBaseRepository{

    private static final Logger logger = LoggerFactory.getLogger(SerieDataBaseRepositoryImpl.class);
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final boolean INCLUDE_ADULT = false;
    private static final boolean INCLUDE_FIRST_AIR_DATE = false;
    private static final String LANGUAGE = "fr-FR";
    private final TmdbConfig tmdbConfig;

    private final SerieApiMapper serieApiMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public SerieDataBaseRepositoryImpl(TmdbConfig tmdbConfig, SerieApiMapper serieApiMapper) {
        this.tmdbConfig = tmdbConfig;
        this.serieApiMapper = serieApiMapper;
    }

    @Override
    public List<SerieDataBase> searchSeriesDataBase(String query) {
        String url = BASE_URL + "search/tv?query=" + query + "&api_key=" + tmdbConfig.getTmdbApiKey() + "&include_adult=" + INCLUDE_ADULT +  "&language=" + LANGUAGE;

        logger.debug("recherche détail de la liste des  sériee selon chaîne de caractères: " + query + " via appel url: " + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                SearchSeriesResponseDto searchSeriesResponseDto = objectMapper.readValue(jsonResponse, SearchSeriesResponseDto.class);
                return serieApiMapper.mapSearchSeriesResponseDtoToEntityList(searchSeriesResponseDto);
            } else {
                throw handleErrorResponse(response.code(), "recherche de toutes les séries trouvées ayant dans leur titre la chaine de caractères: " + query);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SerieDataBase findDetailSerieDataBase(long idTmdb)  {

        //https://api.themoviedb.org/3/tv/693?&api_key=5f871496b04d6b713429ccba8a599149&language=fr-FR
        String url = BASE_URL + "tv/" + idTmdb + "?&api_key=" + tmdbConfig.getTmdbApiKey() +  "&language=" + LANGUAGE;

        logger.debug("recherche détail d'une série selon id" + idTmdb + " via appel url: " + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                DetailSerieResponseDto detailSerieResponseDto = objectMapper.readValue(jsonResponse, DetailSerieResponseDto.class);
                return serieApiMapper.mapDetailSerieResponseDtoToEntity(detailSerieResponseDto);

            } else {
                System.out.println("dans le else");
                throw handleErrorResponse(response.code(), "recherche du détail de la série d'id Tmdb: " + idTmdb);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SerieDataBase> searchSuggestedSerieDataBase(int page) {
        String url = BASE_URL + "discover/tv?"
                + "&api_key=" + tmdbConfig.getTmdbApiKey()
                + "&include_adult=" + INCLUDE_ADULT
                + "include_null_first_air_dates=" + INCLUDE_FIRST_AIR_DATE
                + "&language=" + LANGUAGE
                + "&page=" + page
                + "&sort_by=" + "popularity.desc"
                + "&with_origin_country=" + "FR%7CUS";  //pays d'origine des séries France et USA
//https://api.themoviedb.org/3/discover/tv?include_adult=false&include_null_first_air_dates=false&language=fr-FR&page=1&sort_by=popularity.desc&with_origin_country=FR%7CUS''

        logger.debug("recherche d'un suggestion de série page: " + page + " via appel url: " + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                SearchSeriesResponseDto searchSeriesResponseDto = objectMapper.readValue(jsonResponse, SearchSeriesResponseDto.class);
                return serieApiMapper.mapSearchSeriesResponseDtoToEntityList(searchSeriesResponseDto);

            } else {
                throw handleErrorResponse(response.code(), "recherche de la liste des séries suggérées pour la page n°: " + page);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Throwable handleErrorResponse(int statusCode, String message) {
        if (statusCode == 404) {
            throw new MediaDataBaseNonTrouveException("aucun résultat pour cette recherche: " + message);
        } else if (statusCode == 401) {
            throw new MediaDataBaseUnAuthorizedAccessException("Accès non autorisé à l'API TMDB");
        } else {
            throw new MediaDataBaseException("Échec de l'appel " + message + " à l'API TMDB avec le code de réponse: " + statusCode);
        }
    }
}
