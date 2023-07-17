package org.epita.exposition.dto.media.serie;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.film.FilmDto;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.media.genre.GenreMapper;
import org.epita.exposition.media.serie.SerieDto;
import org.epita.exposition.media.serie.SerieMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SerieMapper.class})
@ContextConfiguration(classes = {GenreMapper.class})
public class SerieMapperTest {
    @Autowired
    private Mapper<SerieEntity, org.epita.exposition.media.serie.SerieDto> mapper;

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
        SerieEntity serieEntity = new SerieEntity();
        serieEntity.setId(1L);
        serieEntity.setDuree(123);
        serieEntity.setTitre("titre");
        serieEntity.setDateSortie(LocalDate.of(2023,7,13));
        serieEntity.setCheminAffichePaysage("chemin paysage");
        serieEntity.setCheminAffichePortrait("chemin portrait");
        serieEntity.setIdTmdb("123-DFG-567");
        serieEntity.setNoteTmdb(4);
        serieEntity.setGenreList(genreEntityList);
        serieEntity.setNombreSaisons(4);


        // When
        org.epita.exposition.media.serie.SerieDto serieDto = this.mapper.mapEntityToDto(serieEntity);

        // Then
        // pas de getId dans FilmDto
        assertThat(serieDto.getDuree())
                .isEqualTo(serieEntity.getDuree());

        assertThat(serieDto.getTitre())
                .isEqualTo(serieEntity.getTitre());

        assertThat(serieDto.getDateSortie())
                .isEqualTo(serieEntity.getDateSortie());

        assertThat(serieDto.getCheminAffichePaysage())
                .isEqualTo(serieEntity.getCheminAffichePaysage());

        assertThat(serieDto.getCheminAffichePortrait())
                .isEqualTo(serieEntity.getCheminAffichePortrait());

        assertThat(serieDto.getIdTmdb())
                .isEqualTo(serieEntity.getIdTmdb());

        assertThat(serieDto.getNoteTmdb())
                .isEqualTo(serieEntity.getNoteTmdb());

        assertThat(serieDto.getGenreList().size())
                .isEqualTo(serieEntity.getGenreList().size());

        assertThat(serieDto.getNombreSaisons())
                .isEqualTo(serieEntity.getNombreSaisons());
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
        SerieDto serieDto = new SerieDto();
        serieDto.setDuree(123);
        serieDto.setTitre("titre");
        serieDto.setDateSortie(LocalDate.of(2023,7,13));
        serieDto.setCheminAffichePaysage("chemin paysage");
        serieDto.setCheminAffichePortrait("chemin portrait");
        serieDto.setIdTmdb("123-DFG-567");
        serieDto.setNoteTmdb(4);
        serieDto.setGenreList(genreEntityList);
        serieDto.setNombreSaisons(4);

        // When
        SerieEntity serieEntity = this.mapper.mapDtoToEntity(serieDto);

        // Then
        // pas de getId dans FilmDto
        assertThat(serieEntity.getDuree())
                .isEqualTo(serieDto.getDuree());

        assertThat(serieEntity.getTitre())
                .isEqualTo(serieDto.getTitre());

        assertThat(serieEntity.getDateSortie())
                .isEqualTo(serieDto.getDateSortie());

        assertThat(serieEntity.getCheminAffichePaysage())
                .isEqualTo(serieDto.getCheminAffichePaysage());

        assertThat(serieEntity.getCheminAffichePortrait())
                .isEqualTo(serieDto.getCheminAffichePortrait());

        assertThat(serieEntity.getIdTmdb())
                .isEqualTo(serieDto.getIdTmdb());

        assertThat(serieEntity.getNombreSaisons())
                .isEqualTo(serieDto.getNombreSaisons());

        assertThat(serieEntity.getGenreList().size())
                .isEqualTo(serieDto.getGenreList().size());
    }
}
