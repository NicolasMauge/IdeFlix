package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MovieDataBaseException;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.DetailMovieResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchMoviesResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.MovieApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;


@Repository
public class MovieDataBaseRepositoryImpl implements MovieDataBaseRepository {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseRepositoryImpl.class);
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final boolean INCLUDE_ADULT = false;
    private static final String LANGUAGE = "fr-FR";
    private TmdbConfig tmdbConfig;

    private MovieApiMapper movieApiMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public MovieDataBaseRepositoryImpl(TmdbConfig tmdbConfig, MovieApiMapper movieApiMapper) {
        this.tmdbConfig = tmdbConfig;
        this.movieApiMapper = movieApiMapper;
    }

    @Override
    public List<MovieDataBase> searchMovieDataBase(String query)  {
        String url = BASE_URL + "search/movie?query=" + query + "&api_key=" + tmdbConfig.getTmdbApiKey() + "&include_adult=" + INCLUDE_ADULT +  "&language=" + LANGUAGE;

//        System.out.println(url);

        logger.debug("recherche liste films selon " + query);

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
                    List<MovieDataBase> listMovieDataBase = movieApiMapper.mapSearchMoviesResponseDtoToEntityList(searchMoviesResponseDto);
                    return listMovieDataBase;

                } else {
                    throw new MovieDataBaseException("APP - Tmdb - Echec recherche liste de films avec caractères:  " +  query + " avec un code retour API: " + response.code());
                }
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MovieDataBase findDetailMovieDataBase(long idTmdb) {
        String url = BASE_URL + "movie/" + idTmdb + "?&api_key=" + tmdbConfig.getTmdbApiKey() +  "&language=" + LANGUAGE;

//        System.out.println(url);

        logger.debug("recherche détail d'un films selon id" + idTmdb);

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
                DetailMovieResponseDto detailMovieResponseDto = objectMapper.readValue(jsonResponse, DetailMovieResponseDto.class);
                MovieDataBase movieDataBase = movieApiMapper.mapDetailMovieResponseDtoToEntity(detailMovieResponseDto);
                return movieDataBase;

            } else {
                throw new MovieDataBaseException("APP - Tmdb - Echec recherche du détail du film d'Id TMDB:  " +  idTmdb + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}


