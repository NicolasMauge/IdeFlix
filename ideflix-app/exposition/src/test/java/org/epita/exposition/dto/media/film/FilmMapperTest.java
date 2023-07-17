package org.epita.exposition.dto.media.film;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.film.FilmDto;
import org.epita.exposition.media.film.FilmMapper;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.media.genre.GenreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FilmMapper.class})
@ContextConfiguration(classes = {GenreMapper.class})
public class FilmMapperTest {
    @Autowired
    private Mapper<FilmEntity, FilmDto> mapper;

    @Test
    public void mapEntityToDto() {
        // Given
        // Liste de genres
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1L);
        genreEntity.setNomGenre("nom genre");
        genreEntity.setIdTmdb("1535-Dfetf432");

        GenreEntity genreEntity2 = new GenreEntity();
        genreEntity2.setId(2L);
        genreEntity2.setNomGenre("nom genre 2");
        genreEntity2.setIdTmdb("675-dfg-456");

        List<GenreEntity> genreEntityList = new ArrayList<>();
        genreEntityList.add(genreEntity);
        genreEntityList.add(genreEntity2);

        // Films
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setId(1L);
        filmEntity.setDuree(123);
        filmEntity.setTitre("titre");
        filmEntity.setDateSortie(LocalDate.of(2023,7,13));
        filmEntity.setCheminAffichePaysage("chemin paysage");
        filmEntity.setCheminAffichePortrait("chemin portrait");
        filmEntity.setIdTmdb("123-DFG-567");
        filmEntity.setNoteTmdb(4);
        filmEntity.setGenreList(genreEntityList);

        // When
        FilmDto filmDto = this.mapper.mapEntityToDto(filmEntity);

        // Then
        // pas de getId dans FilmDto
        assertThat(filmDto.getDuree())
                .isEqualTo(filmEntity.getDuree());

        assertThat(filmDto.getTitre())
                .isEqualTo(filmEntity.getTitre());

        assertThat(filmDto.getDateSortie())
                .isEqualTo(filmEntity.getDateSortie());

        assertThat(filmDto.getCheminAffichePaysage())
                .isEqualTo(filmEntity.getCheminAffichePaysage());

        assertThat(filmDto.getCheminAffichePortrait())
                .isEqualTo(filmEntity.getCheminAffichePortrait());

        assertThat(filmDto.getIdTmdb())
                .isEqualTo(filmEntity.getIdTmdb());

        assertThat(filmDto.getNoteTmdb())
                .isEqualTo(filmEntity.getNoteTmdb());

        assertThat(filmDto.getGenreList().size())
                .isEqualTo(filmEntity.getGenreList().size());
    }

    @Test
    public void mapDtoToEntity() {
        // Given
        // Liste de genres
        GenreDto genreDto = new GenreDto();
        genreDto.setId(1L);
        genreDto.setNomGenre("nom genre");
        genreDto.setIdTmdb("1535-Dfetf432");

        GenreDto genreDto2 = new GenreDto();
        genreDto2.setId(2L);
        genreDto2.setNomGenre("nom genre 2");
        genreDto2.setIdTmdb("675-dfg-456");

        List<GenreDto> genreEntityList = new ArrayList<>();
        genreEntityList.add(genreDto);
        genreEntityList.add(genreDto2);

        // Films
        FilmDto filmDto = new FilmDto();
        filmDto.setDuree(123);
        filmDto.setTitre("titre");
        filmDto.setDateSortie(LocalDate.of(2023,7,13));
        filmDto.setCheminAffichePaysage("chemin paysage");
        filmDto.setCheminAffichePortrait("chemin portrait");
        filmDto.setIdTmdb("123-DFG-567");
        filmDto.setNoteTmdb(4);
        filmDto.setGenreList(genreEntityList);

        // When
        FilmEntity filmEntity = this.mapper.mapDtoToEntity(filmDto);

        // Then
        // pas de getId dans FilmDto
        assertThat(filmEntity.getDuree())
                .isEqualTo(filmDto.getDuree());

        assertThat(filmEntity.getTitre())
                .isEqualTo(filmDto.getTitre());

        assertThat(filmEntity.getDateSortie())
                .isEqualTo(filmDto.getDateSortie());

        assertThat(filmEntity.getCheminAffichePaysage())
                .isEqualTo(filmDto.getCheminAffichePaysage());

        assertThat(filmEntity.getCheminAffichePortrait())
                .isEqualTo(filmDto.getCheminAffichePortrait());

        assertThat(filmEntity.getIdTmdb())
                .isEqualTo(filmDto.getIdTmdb());

        assertThat(filmEntity.getNoteTmdb())
                .isEqualTo(filmDto.getNoteTmdb());

        assertThat(filmEntity.getGenreList().size())
                .isEqualTo(filmDto.getGenreList().size());
    }
}
