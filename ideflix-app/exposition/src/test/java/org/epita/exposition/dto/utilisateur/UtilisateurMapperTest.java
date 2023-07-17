package org.epita.exposition.dto.utilisateur;

import org.assertj.core.api.Assert;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurDto;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurMapper;
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
@ContextConfiguration(classes = {UtilisateurMapper.class})
public class UtilisateurMapperTest {
    @Autowired
    UtilisateurMapper mapper;

    @Test
    public void mapEntityToDto() {
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
        assertThat(utilisateurDto.getId()).isEqualTo(1L);
        assertThat(utilisateurDto.getEmail()).isEqualTo("email");
        assertThat(utilisateurDto.getPrenom()).isEqualTo("prenom");
        assertThat(utilisateurDto.getNom()).isEqualTo("nom");
        assertThat(utilisateurDto.getDateCreation()).isEqualTo(LocalDate.of(2023, 07, 17));
    }

    @Test
    public void mapDtoToEntity() {
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
        assertThat(utilisateur.getId()).isEqualTo(1L);
        assertThat(utilisateur.getEmail()).isEqualTo("email");
        assertThat(utilisateur.getPrenom()).isEqualTo("prenom");
        assertThat(utilisateur.getNom()).isEqualTo("nom");
        assertThat(utilisateur.getDateCreation()).isEqualTo(LocalDate.of(2023, 07, 17));
        assertThat(utilisateur.getPreferencesUtilisateurEntity()).isEqualTo(null);
    }
}
