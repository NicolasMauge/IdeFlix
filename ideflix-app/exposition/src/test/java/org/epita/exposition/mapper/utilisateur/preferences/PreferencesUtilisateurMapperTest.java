package org.epita.exposition.mapper.utilisateur.preferences;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.application.utilisateur.utilisateur.UtilisateurServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.epita.exposition.mapper.utilisateur.PreferencesUtilisateurMapper;
import org.epita.infrastructure.utilisateur.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.epita.exposition.common.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {PreferencesUtilisateurMapper.class})
@ContextConfiguration(classes = {GenreMapper.class, UtilisateurServiceImpl.class})
public class PreferencesUtilisateurMapperTest {
    @Autowired
    Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> mapper;

    @MockBean
    UtilisateurRepository utilisateurRepositoryMock;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        // Utilisateur
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        // Liste de genres
        List<GenreEntity> genreEntityList = new ArrayList<>();
        genreEntityList
                .add(new GenreEntity(1L, "1535-Dfetf432", "nom genre"));
        genreEntityList.add(
                new GenreEntity(2L, "675-dfg-456", "nom genre 2"));

        // Preférences
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = new PreferencesUtilisateurEntity(1L, "pseudo", utilisateur, genreEntityList);

        // When
        PreferencesUtilisateurDto preferencesUtilisateurDto = this.mapper.mapEntityToDto(preferencesUtilisateurEntity);

        // Then
        assertThat(preferencesUtilisateurDto.getPseudo())
                .isEqualTo(preferencesUtilisateurEntity.getPseudo());

        assertThat(preferencesUtilisateurDto.getGenreList().size())
                .isEqualTo(preferencesUtilisateurEntity.getGenreList().size());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        // Utilisateur
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        // Liste de genres
        List<GenreDto> genreDtoList = new ArrayList<>();
        genreDtoList
                .add(new GenreDto(1L, "1535-Dfetf432", "nom genre"));
        genreDtoList.add(
                new GenreDto(2L, "675-dfg-456", "nom genre 2"));

        // Preférences
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto("pseudo", "test@test.com", genreDtoList);

        when(utilisateurRepositoryMock.findByEmail("test@test.com")).thenReturn(Optional.of(utilisateur));

        // When
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = this.mapper.mapDtoToEntity(preferencesUtilisateurDto);

        // Then
        assertThat(preferencesUtilisateurEntity.getPseudo())
                .isEqualTo(preferencesUtilisateurDto.getPseudo());

        assertThat(preferencesUtilisateurEntity.getGenreList().size())
                .isEqualTo(preferencesUtilisateurDto.getGenreList().size());
    }
}
