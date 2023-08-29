package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MediaDataBaseException;
import org.epita.domaine.common.MediaDataBaseNonTrouveException;
import org.epita.domaine.common.MediaDataBaseUnAuthorizedAccessException;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.DetailMovieResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchMoviesResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.MovieApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MovieDataBaseRepositoryImpl implements MovieDataBaseRepository {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseRepositoryImpl.class);
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final boolean INCLUDE_ADULT = false;
    private static final String LANGUAGE = "fr-FR";
    private final TmdbConfig tmdbConfig;

    private final MovieApiMapper movieApiMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public MovieDataBaseRepositoryImpl(TmdbConfig tmdbConfig, MovieApiMapper movieApiMapper) {
        this.tmdbConfig = tmdbConfig;
        this.movieApiMapper = movieApiMapper;
    }

    @Override
    public List<MovieDataBase> searchAllMovieDataBaseWithQuery(String query) {
        String url = BASE_URL + "search/movie?query=" + query + "&api_key=" + tmdbConfig.getTmdbApiKey() + "&include_adult=" + INCLUDE_ADULT + "&language=" + LANGUAGE;


        logger.debug("recherche liste des films trouvés selon la chaîne de caractères " + query + " via appel url: " + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                SearchMoviesResponseDto searchMoviesResponseDto = objectMapper.readValue(jsonResponse, SearchMoviesResponseDto.class);
                return movieApiMapper.mapSearchMoviesResponseDtoToEntityList(searchMoviesResponseDto);

            } else {
                throw handleErrorResponse(response.code(), "recherche de tous les films trouvés ayant dans leur titre la chaine de caractères: " + query);
            }
        } catch (Throwable e) {
            throw new MediaDataBaseException("Echec recherche liste de films avec caractères:  " + query + " avec un code retour API: " + e);
        }
    }

    @Override
    public MovieDataBase findDetailMovieDataBase(long idTmdb) {
        String url = BASE_URL + "movie/" + idTmdb + "?&api_key=" + tmdbConfig.getTmdbApiKey() + "&language=" + LANGUAGE;


        logger.debug("recherche du détail d'un film selon id" + idTmdb + " via appel url: " + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                DetailMovieResponseDto detailMovieResponseDto = objectMapper.readValue(jsonResponse, DetailMovieResponseDto.class);
                return movieApiMapper.mapDetailMovieResponseDtoToEntity(detailMovieResponseDto);

            } else {
                throw handleErrorResponse(response.code(), "recherche du détail du Film d'id Tmdb: " + idTmdb);
            }
        } catch (Throwable e) {
            throw new MediaDataBaseException("APP - Tmdb - Echec recherche du détail du film d'Id Tmdb:  " + idTmdb + " avec un code retour API: " + e);
        }
    }

    @Override
    public List<MovieDataBase> searchSuggestedMovieDatabase(int page) {
        String url = BASE_URL + "discover/movie?"
                + "&api_key=" + tmdbConfig.getTmdbApiKey()
                + "&include_adult=" + INCLUDE_ADULT
                + "&language=" + LANGUAGE
                + "&page=" + page
                + "&sort_by=" + "popularity.desc"
                + "&with_origin_country=" + "FR%7CUS";  //pays d'origine des séries France et USA;
        https:
//api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=fr-FR&page=1&sort_by=popularity.desc&with_origin_country=FR%7CUS'

        logger.debug("recherche d'une suggestion de films page: " + page + " via appel url: " + url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                SearchMoviesResponseDto searchMoviesResponseDto = objectMapper.readValue(jsonResponse, SearchMoviesResponseDto.class);
                return movieApiMapper.mapSearchMoviesResponseDtoToEntityList(searchMoviesResponseDto);

            } else {
                throw handleErrorResponse(response.code(), "recherche de la liste des films suggérés pour la page n°: " + page);
            }
        } catch (Throwable e) {
            throw new MediaDataBaseException("APP - Tmdb - Echec recherche suggestion de films de la page:  " + page + " avec un code retour API: " + e);
        }
    }

    private Throwable handleErrorResponse(int statusCode, String message) {
        if (statusCode == 404) {
            throw new MediaDataBaseNonTrouveException(" pour cette recherche: " + message);
        } else if (statusCode == 401) {
            throw new MediaDataBaseUnAuthorizedAccessException("Accès non autorisé à l'API TMDB");
        } else {
            throw new MediaDataBaseException("Échec de l'appel " + message + " à l'API TMDB avec le code de réponse: " + statusCode);
        }
    }
}


