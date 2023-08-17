package org.epita.exposition.mediaDataBase.mapper;


import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MovieDataBaseMapper extends Mapper<MovieDataBase, MediaDataBaseResponseDto> {
    private Mapper<GenreDataBase, GenreDataBaseResponseDto> genreDataBaseMapper;

    public MovieDataBaseMapper(Mapper<GenreDataBase, GenreDataBaseResponseDto> genreDataBaseMapper) {
        this.genreDataBaseMapper = genreDataBaseMapper;
    }

    @Override
    public MediaDataBaseResponseDto mapEntityToDto(MovieDataBase input) {

        int nombreSaisons = 0;
        TypeMedia typeMedia = TypeMedia.FILM;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = input.getDateSortie() != null && !input.getDateSortie().equals(LocalDate.of(1900, 1 , 1))?
                               input.getDateSortie().format(dateFormatter) : "Date inconnue";



        return new MediaDataBaseResponseDto(
                input.getIdDataBase(),
                input.getTitre(),
                formattedDate,
                input.getDuree(),
                input.getResume(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteDataBase(),
                this.genreDataBaseMapper.mapListEntityToDto(input.getGenres()),
                TypeMedia.FILM,
                nombreSaisons);
    }

    @Override
    public MovieDataBase mapDtoToEntity(MediaDataBaseResponseDto input) {
        return null;
    }

}