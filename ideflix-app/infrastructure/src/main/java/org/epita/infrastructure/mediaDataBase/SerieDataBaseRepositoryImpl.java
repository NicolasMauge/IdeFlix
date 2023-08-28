package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MediaDataBaseException;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.DetailSerieResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchSeriesResponseDto;
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
                return serieApiMapper.mapSearchSeriesResponseDtoToEntityList(searchSeriesResponseDto);
            } else {
                throw new MediaDataBaseException("APP - Tmdb - Echec recherche liste de séries avec caractères:  " +  query + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SerieDataBase findDetailSerieDataBase(long idTmdb) {

        //https://api.themoviedb.org/3/tv/693?&api_key=5f871496b04d6b713429ccba8a599149&language=fr-FR
        String url = BASE_URL + "tv/" + idTmdb + "?&api_key=" + tmdbConfig.getTmdbApiKey() +  "&language=" + LANGUAGE;

        System.out.println(url);

        logger.debug("recherche détail d'une série selon id" + idTmdb);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string(); // réponse JSON brute en tant que chaîne
                System.out.println("retour: " + jsonResponse);
                DetailSerieResponseDto detailSerieResponseDto = objectMapper.readValue(jsonResponse, DetailSerieResponseDto.class);
                return serieApiMapper.mapDetailSerieResponseDtoToEntity(detailSerieResponseDto);

            } else {
                throw new MediaDataBaseException("APP - Tmdb - Echec recherche du détail de la série d'Id TMDB:  " +  idTmdb + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
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
        System.out.println(url);

        logger.debug("recherche d'un suggestion de série page: " + page);

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
//                System.out.println("retour repository: " + serieApiMapper.mapSearchSeriesResponseDtoToEntityList(searchSeriesResponseDto));
                return serieApiMapper.mapSearchSeriesResponseDtoToEntityList(searchSeriesResponseDto);

            } else {
                throw new MediaDataBaseException("APP - Tmdb - Echec recherche suggestion de séries de la page:  " + page + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
