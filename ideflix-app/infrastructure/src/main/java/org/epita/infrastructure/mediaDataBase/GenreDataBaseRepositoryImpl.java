package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MediaDataBaseException;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.AllGenresResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.GenreApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class GenreDataBaseRepositoryImpl implements GenreDataBaseRepository {

    private static final Logger logger = LoggerFactory.getLogger(GenreDataBaseRepositoryImpl.class);
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final String LANGUAGE = "fr-FR";
    private final TmdbConfig tmdbConfig;

    private final GenreApiMapper genreApiMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public GenreDataBaseRepositoryImpl(TmdbConfig tmdbConfig, GenreApiMapper genreApiMapper) {
        this.tmdbConfig = tmdbConfig;
        this.genreApiMapper = genreApiMapper;
    }

    @Override
    public List<GenreDataBase> searchAllGenresForTV() {
        //https://api.themoviedb.org/3/genre/tv/list?language=fr
        return searchAllGenres("genre/tv/list");
    }

    @Override
    public List<GenreDataBase> searchAllGenresForMovie() {

        //https://api.themoviedb.org/3/genre/movie/list?language=fr
        return searchAllGenres("genre/movie/list");
    }

    private List<GenreDataBase> searchAllGenres(String endpoint) {
        String url = BASE_URL + endpoint + "?api_key=" + tmdbConfig.getTmdbApiKey() + "&language=" + LANGUAGE;

        System.out.println(url);

        String itemType = endpoint.contains("movie") ? "films" : "séries";
        logger.debug("recherche liste genres des " + itemType);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string();
                AllGenresResponseDto allGenresResponseDto = objectMapper.readValue(jsonResponse, AllGenresResponseDto.class);
                return genreApiMapper.mapFromGenreResponseDtoList(allGenresResponseDto.getGenres());
            } else {
                throw new MediaDataBaseException("APP - Tmdb - Echec recherche liste des genres pour " + itemType + " avec un code retour API: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
