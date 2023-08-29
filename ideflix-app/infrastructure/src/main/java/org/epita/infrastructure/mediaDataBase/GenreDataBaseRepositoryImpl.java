package org.epita.infrastructure.mediaDataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.epita.domaine.common.MediaDataBaseException;
import org.epita.domaine.common.MediaDataBaseNonTrouveException;
import org.epita.domaine.common.MediaDataBaseUnAuthorizedAccessException;
import org.epita.domaine.media.GenreEntity;
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
    public List<GenreEntity> searchAllGenresEntityForTV() {
        //https://api.themoviedb.org/3/genre/tv/list?language=fr
        return searchAllGenresEntity("genre/tv/list");
    }

    @Override
    public List<GenreEntity> searchAllGenresEntityForMovie() {
        //https://api.themoviedb.org/3/genre/movie/list?language=fr
        return searchAllGenresEntity("genre/movie/list");
    }

    private List<GenreEntity> searchAllGenresEntity(String endpoint) {
        String url = BASE_URL + endpoint + "?api_key=" + tmdbConfig.getTmdbApiKey() + "&language=" + LANGUAGE;

        String itemType = endpoint.contains("movie") ? "films" : "séries";
        logger.debug("recherche liste genres des " + itemType + " via appel url: " + url);

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
                return genreApiMapper.mapFromGenreResponseDtoListEntity(allGenresResponseDto.getGenres());
            } else {
                throw handleErrorResponse(response.code(), "recherche de la liste de tous les genres" );
            }
        } catch (Throwable e) {
            throw new MediaDataBaseException("APP - Tmdb - Echec recherche liste des genres pour " + itemType + " avec un code retour API: " + e);
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
