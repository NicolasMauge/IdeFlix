package org.epita.exposition.mapper.media.genre;

import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper extends Mapper<GenreEntity, GenreDto> {
    @Override
    public GenreDto mapEntityToDto(GenreEntity input) {
        return new GenreDto(input.getId(), input.getIdTmdb(), input.getNomGenre());
    }

    @Override
    public GenreEntity mapDtoToEntity(GenreDto input) {
        return new GenreEntity(input.getId(), input.getIdTmdb(), input.getNomGenre());
    }
}
