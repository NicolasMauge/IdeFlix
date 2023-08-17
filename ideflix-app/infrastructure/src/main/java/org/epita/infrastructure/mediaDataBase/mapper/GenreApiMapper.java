package org.epita.infrastructure.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.GenreResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreApiMapper {

    public static List<GenreDataBase> mapFromGenreResponseDtoList(List<GenreResponseDto> genreResponseDtos) {
        List<GenreDataBase> genreDataBases = new ArrayList<>();
        for (GenreResponseDto genreResponseDto : genreResponseDtos) {
            GenreDataBase genreDataBase = new GenreDataBase(genreResponseDto.getId(), genreResponseDto.getName());
            genreDataBases.add(genreDataBase);
        }
        return genreDataBases;
    }
}
