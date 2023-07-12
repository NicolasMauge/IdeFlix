package org.epita.exposition.media.genre;

import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper extends Mapper<GenreEntity, GenreDto> {
    @Override
    public GenreDto mapEntityToDto(GenreEntity input) {
        return new GenreDto(input.getId(), input.getIdTmdb(), input.getNomGenre());
    }

    @Override
    public GenreEntity mapDtoToEntity(GenreDto input) {
        return new GenreEntity(input.getIdTmdb(), input.getNomGenre());
    }
}
