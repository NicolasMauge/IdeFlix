package org.epita.exposition.dto.utilisateur.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreMapper;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurDto;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurMapper;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurDto;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurEtPrefDto;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurEtPrefMapper;
import org.epita.infrastructure.utilisateur.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { UtilisateurEtPrefMapper.class})
@ContextConfiguration(classes = {PreferencesUtilisateurMapper.class, GenreMapper.class})
public class UtilisateurEtPrefMapperTest {
    @Autowired
    private Mapper<UtilisateurEntity, UtilisateurEtPrefDto> mapper;

    @Test
    public void mapEntityToDto() {
        // Given
        // Preferences Utilisateurs
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = new PreferencesUtilisateurEntity();
        preferencesUtilisateurEntity.setId(199L);
        preferencesUtilisateurEntity.setGenreList(new ArrayList<>());

        // Utilisateur
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("email");
        utilisateur.setPrenom("prenom");
        utilisateur.setNom("nom");
        utilisateur.setDateCreation(LocalDate.of(2023, 07, 17));
        utilisateur.setPreferencesUtilisateurEntity(preferencesUtilisateurEntity);

        // When
        UtilisateurEtPrefDto utilisateurEtPrefDto = mapper.mapEntityToDto(utilisateur);

        // Then
        assertThat(utilisateurEtPrefDto.getId())
                .isEqualTo(utilisateur.getId());

        assertThat(utilisateurEtPrefDto.getEmail())
                .isEqualTo(utilisateur.getEmail());

        assertThat(utilisateurEtPrefDto.getPrenom())
                .isEqualTo(utilisateur.getPrenom());

        assertThat(utilisateurEtPrefDto.getNom())
                .isEqualTo(utilisateur.getNom());

        assertThat(utilisateurEtPrefDto.getDateCreation())
                .isEqualTo(utilisateur.getDateCreation());

        assertThat(utilisateurEtPrefDto.getPreferencesUtilisateur().getId())
                .isEqualTo(utilisateur.getPreferencesUtilisateurEntity().getId());
    }

    @Test
    public void mapDtoToEntity() {
        // Given
        // Preferences Utilisateurs
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto();
        preferencesUtilisateurDto.setId(199L);
        preferencesUtilisateurDto.setGenreList(new ArrayList<>());

        // Utilisateur
        UtilisateurEtPrefDto utilisateurEtPrefDto = new UtilisateurEtPrefDto();
        utilisateurEtPrefDto.setId(1L);
        utilisateurEtPrefDto.setEmail("email");
        utilisateurEtPrefDto.setPrenom("prenom");
        utilisateurEtPrefDto.setNom("nom");
        utilisateurEtPrefDto.setDateCreation(LocalDate.of(2023, 07, 17));
        utilisateurEtPrefDto.setPreferencesUtilisateur(preferencesUtilisateurDto);

        // When
        UtilisateurEntity utilisateur = mapper.mapDtoToEntity(utilisateurEtPrefDto);

        // Then
        assertThat(utilisateur.getId())
                .isEqualTo(utilisateurEtPrefDto.getId());

        assertThat(utilisateur.getEmail())
                .isEqualTo(utilisateurEtPrefDto.getEmail());

        assertThat(utilisateur.getPrenom())
                .isEqualTo(utilisateurEtPrefDto.getPrenom());

        assertThat(utilisateur.getNom())
                .isEqualTo(utilisateurEtPrefDto.getNom());

        assertThat(utilisateur.getDateCreation())
                .isEqualTo(utilisateurEtPrefDto.getDateCreation());

        assertThat(utilisateur.getPreferencesUtilisateurEntity().getId())
                .isEqualTo(utilisateurEtPrefDto.getPreferencesUtilisateur().getId());
    }
}
