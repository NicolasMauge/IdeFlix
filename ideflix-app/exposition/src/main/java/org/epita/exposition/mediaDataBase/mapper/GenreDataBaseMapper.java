package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.springframework.stereotype.Component;

@Component
public class GenreDataBaseMapper extends Mapper<GenreDataBase, GenreDataBaseResponseDto> {

    @Override
    public GenreDataBaseResponseDto mapEntityToDto(GenreDataBase input) {
        return new GenreDataBaseResponseDto(input.getIdDatabase(), input.getNomGenre());
    }

    @Override
    public GenreDataBase mapDtoToEntity(GenreDataBaseResponseDto input) {
        return new GenreDataBase(input.getIdDataBase(), input.getNomGenre());
    }
}