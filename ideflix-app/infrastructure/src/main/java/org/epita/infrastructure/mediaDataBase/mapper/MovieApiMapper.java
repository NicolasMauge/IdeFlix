package org.epita.infrastructure.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.DetailMovieResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.MovieLightResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchMoviesResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieApiMapper {

//    GenreApiMapper genreMapper;
//
//    public MovieApiMapper(GenreApiMapper genreMapper) {
//        this.genreMapper = genreMapper;
//    }

    public MovieDataBase mapMovieLightResponseDtoToEntity(MovieLightResponseDto movieLightResponseDto) {

        int duree = 0;

        List<GenreDataBase> listGenres = new ArrayList<>();
        for (int i = 0; i < movieLightResponseDto.getGenre_ids().size(); i++) {
            listGenres.add(i,new GenreDataBase(movieLightResponseDto.getGenre_ids().get(i),""));
        }

        LocalDate dateSortie;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        if (movieLightResponseDto.getRelease_date() != null && !movieLightResponseDto.getRelease_date().isEmpty()) {
            dateSortie = LocalDate.parse(movieLightResponseDto.getRelease_date(), dateFormatter);
        } else {
            dateSortie = LocalDate.of(1900, 1, 1); // valeur par défaut quand date inexistante
        }

        return new MovieDataBase(movieLightResponseDto.getId(),
                            movieLightResponseDto.getTitle(),
                            listGenres,
                            movieLightResponseDto.getOverview(),
                            movieLightResponseDto.getPoster_path(),
                            movieLightResponseDto.getBackdrop_path(),
                            dateSortie,
                            duree,
                            movieLightResponseDto.getVote_average());
    }

    public List<MovieDataBase> mapSearchMoviesResponseDtoToEntityList(SearchMoviesResponseDto searchMoviesResponseDto) {

        List<MovieDataBase> movieDataBaseList = new ArrayList<>();

        for (MovieLightResponseDto movieLightResponseDto : searchMoviesResponseDto.getResults()) {
            movieDataBaseList.add(mapMovieLightResponseDtoToEntity(movieLightResponseDto));
        }

        return movieDataBaseList;
    }

    public MovieDataBase mapDetailMovieResponseDtoToEntity(DetailMovieResponseDto detailMovieResponseDto) {


        List<GenreDataBase> genreDataBases = GenreApiMapper.mapFromGenreResponseDtoList(detailMovieResponseDto.getGenres());

        LocalDate dateSortie;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        if (detailMovieResponseDto.getRelease_date() != null && !detailMovieResponseDto.getRelease_date().isEmpty()) {
            dateSortie = LocalDate.parse(detailMovieResponseDto.getRelease_date(), dateFormatter);
            System.out.println("date sortie: " + dateSortie);
        } else {
            dateSortie = LocalDate.of(1900, 1, 1); // valeur par défaut quand date inexistante
        }

        return new MovieDataBase(detailMovieResponseDto.getId(),
                detailMovieResponseDto.getTitle(),
                genreDataBases,
                detailMovieResponseDto.getOverview(),
                detailMovieResponseDto.getPoster_path(),
                detailMovieResponseDto.getBackdrop_path(),
                dateSortie,
                detailMovieResponseDto.getRuntime(),
                detailMovieResponseDto.getVote_average());
    }

}

