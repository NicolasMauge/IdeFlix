package org.epita.exposition.dto.utilisateur.preferences;

import org.epita.application.media.serie.SerieServiceImpl;
import org.epita.application.utilisateur.utilisateur.UtilisateurServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.media.genre.GenreMapper;
import org.epita.exposition.selection.etiquette.EtiquetteMapper;
import org.epita.exposition.selection.serieselectionnee.SerieSelectionneeMapper;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurDto;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurMapper;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.epita.exposition.common.Mapper;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PreferencesUtilisateurMapper.class})
@ContextConfiguration(classes = {GenreMapper.class})
public class PreferencesUtilisateurMapperTest {
    @Autowired
    Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> mapper;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        // Liste de genres
        List<GenreEntity> genreEntityList = new ArrayList<>();
        genreEntityList
                .add(new GenreEntity(1L, "1535-Dfetf432", "nom genre"));
        genreEntityList.add(
                new GenreEntity(2L, "675-dfg-456", "nom genre 2"));

        // Preférences
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = new PreferencesUtilisateurEntity(1L, "pseudo", genreEntityList);

        // When
        PreferencesUtilisateurDto preferencesUtilisateurDto = this.mapper.mapEntityToDto(preferencesUtilisateurEntity);

        // Then
        assertThat(preferencesUtilisateurDto.getId())
                .isEqualTo(preferencesUtilisateurEntity.getId());

        assertThat(preferencesUtilisateurDto.getPseudo())
                .isEqualTo(preferencesUtilisateurEntity.getPseudo());

        assertThat(preferencesUtilisateurDto.getGenreList().size())
                .isEqualTo(preferencesUtilisateurEntity.getGenreList().size());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        // Liste de genres
        List<GenreDto> genreDtoList = new ArrayList<>();
        genreDtoList
                .add(new GenreDto(1L, "1535-Dfetf432", "nom genre"));
        genreDtoList.add(
                new GenreDto(2L, "675-dfg-456", "nom genre 2"));

        // Preférences
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto(1L, "pseudo", genreDtoList);

        // When
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = this.mapper.mapDtoToEntity(preferencesUtilisateurDto);

        // Then
        assertThat(preferencesUtilisateurEntity.getId())
                .isEqualTo(preferencesUtilisateurDto.getId());

        assertThat(preferencesUtilisateurEntity.getPseudo())
                .isEqualTo(preferencesUtilisateurDto.getPseudo());

        assertThat(preferencesUtilisateurEntity.getGenreList().size())
                .isEqualTo(preferencesUtilisateurDto.getGenreList().size());
    }
}
