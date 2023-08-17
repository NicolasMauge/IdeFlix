package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GenreDataBaseMapper.class})
@ContextConfiguration(classes = {})
public class GenreDataBaseMapperTest {
    @Autowired
    private Mapper<GenreDataBase, GenreDataBaseResponseDto> mapper;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        GenreDataBase genreEntity = new GenreDataBase(18, "Action");

        // When
        GenreDataBaseResponseDto genreDto = this.mapper.mapEntityToDto(genreEntity);

        // Then

        assertThat(genreDto.getNomGenre())
                .isEqualTo(genreEntity.getNomGenre());

        assertThat(genreDto.getIdDataBase())
                .isEqualTo(genreEntity.getIdDatabase());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        GenreDataBaseResponseDto genreDto = new GenreDataBaseResponseDto(18, "Action");

        // When
        GenreDataBase genreEntity = this.mapper.mapDtoToEntity(genreDto);

        // Then

        assertThat(genreEntity.getNomGenre())
                .isEqualTo(genreDto.getNomGenre());

        assertThat(genreEntity.getIdDatabase())
                .isEqualTo(genreDto.getIdDataBase());
    }

    @Test
    public void should_return_mapListEntityToDto() {
        // Given
        List<GenreDataBase> listGenreEntity = new ArrayList<>();
        listGenreEntity.add(new GenreDataBase(18, "Action"));
        listGenreEntity.add(new GenreDataBase(20, "Drame"));

        // When
        List<GenreDataBaseResponseDto> listGenreDto = this.mapper.mapListEntityToDto(listGenreEntity);

        // Then
        assertThat(listGenreDto)
                .extracting("idDataBase", "nomGenre")
                .containsExactly(
                        tuple(18, "Action"),
                        tuple(20, "Drame"));
    }

}
