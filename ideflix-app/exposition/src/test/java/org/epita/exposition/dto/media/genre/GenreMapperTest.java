package org.epita.exposition.dto.media.genre;

import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.media.genre.GenreMapper;
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
    public void mapEntityToDto() {
        // Given
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1L);
        genreEntity.setNomGenre("nom genre");
        genreEntity.setIdTmdb("1535-Dfetf432");

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
    public void mapDtoToEntity() {
        // Given
        GenreDto genreDto = new GenreDto();
        genreDto.setId(1L);
        genreDto.setNomGenre("nom genre");
        genreDto.setIdTmdb("1535-Dfetf432");

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
