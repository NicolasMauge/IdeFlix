package org.epita.exposition.mapper.utilisateur.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.utilisateur.UtilisateurDto;
import org.epita.exposition.mapper.utilisateur.UtilisateurMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { UtilisateurMapper.class})
@ContextConfiguration(classes = {})
public class UtilisateurMapperTest {
    @Autowired
    private Mapper<UtilisateurEntity, UtilisateurDto> mapper;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        // Preferences Utilisateurs
        PreferencesUtilisateurEntity preferencesUtilisateurEntity = new PreferencesUtilisateurEntity();
        preferencesUtilisateurEntity.setId(1L);

        // Utilisateur
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("email");
        utilisateur.setPrenom("prenom");
        utilisateur.setNom("nom");
        utilisateur.setDateCreation(LocalDate.of(2023, 07, 17));
        utilisateur.setPreferencesUtilisateurEntity(preferencesUtilisateurEntity);

        // When
        UtilisateurDto utilisateurDto = mapper.mapEntityToDto(utilisateur);

        // Then
        assertThat(utilisateurDto.getId())
                .isEqualTo(utilisateur.getId());

        assertThat(utilisateurDto.getEmail())
                .isEqualTo(utilisateur.getEmail());

        assertThat(utilisateurDto.getPrenom())
                .isEqualTo(utilisateur.getPrenom());

        assertThat(utilisateurDto.getNom())
                .isEqualTo(utilisateur.getNom());

        assertThat(utilisateurDto.getDateCreation())
                .isEqualTo(utilisateur.getDateCreation());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        // Utilisateur
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setEmail("email");
        utilisateurDto.setPrenom("prenom");
        utilisateurDto.setNom("nom");
        utilisateurDto.setDateCreation(LocalDate.of(2023, 07, 17));

        // When
        UtilisateurEntity utilisateur = mapper.mapDtoToEntity(utilisateurDto);

        // Then
        assertThat(utilisateur.getId())
                .isEqualTo(utilisateurDto.getId());

        assertThat(utilisateur.getEmail())
                .isEqualTo(utilisateurDto.getEmail());

        assertThat(utilisateur.getPrenom())
                .isEqualTo(utilisateurDto.getPrenom());

        assertThat(utilisateur.getNom())
                .isEqualTo(utilisateurDto.getNom());

        assertThat(utilisateur.getDateCreation())
                .isEqualTo(utilisateurDto.getDateCreation());

        assertThat(utilisateur.getPreferencesUtilisateurEntity())
                .isEqualTo(null);
    }
}
