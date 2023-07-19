package org.epita.exposition.dto.media.genre;

import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GenreMapper.class})
@ContextConfiguration(classes = {})
public class GenreMapperTest {
    @Autowired
    private Mapper<GenreEntity, GenreDto> mapper;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        GenreEntity genreEntity = new GenreEntity(1L, "1535-Dfetf432", "nom genre");

        // When
        GenreDto genreDto = this.mapper.mapEntityToDto(genreEntity);

        // Then
        assertThat(genreDto.getId())
                .isEqualTo(genreEntity.getId());

        assertThat(genreDto.getNomGenre())
                .isEqualTo(genreEntity.getNomGenre());

        assertThat(genreDto.getIdTmdb())
                .isEqualTo(genreEntity.getIdTmdb());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        GenreDto genreDto = new GenreDto(1L, "1535-Dfetf432", "nom genre");

        // When
        GenreEntity genreEntity = this.mapper.mapDtoToEntity(genreDto);

        // Then
        assertThat(genreEntity.getId())
                .isEqualTo(genreDto.getId());

        assertThat(genreEntity.getNomGenre())
                .isEqualTo(genreDto.getNomGenre());

        assertThat(genreEntity.getIdTmdb())
                .isEqualTo(genreDto.getIdTmdb());
    }
}
