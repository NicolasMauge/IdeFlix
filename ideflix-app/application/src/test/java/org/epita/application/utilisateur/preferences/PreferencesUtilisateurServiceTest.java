package org.epita.application.utilisateur.preferences;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.utilisateur.PreferencesUtilisateurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { PreferencesUtilisateurServiceImpl.class })
public class PreferencesUtilisateurServiceTest {
    @Autowired
    PreferencesUtilisateurService preferencesUtilisateurService;

    @MockBean
    PreferencesUtilisateurRepository repositoryMock;

    PreferencesUtilisateurEntity preferencesUtilisateur;

    UtilisateurEntity utilisateur;

    @BeforeEach
    public void setUp() {
        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        preferencesUtilisateur = new PreferencesUtilisateurEntity();
        preferencesUtilisateur.setId(1L);
        preferencesUtilisateur.setPseudo("pseudo 1");
        preferencesUtilisateur.setUtilisateur(this.utilisateur);

        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setNomGenre("genre 1");
        genreEntityList.add(genre);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setNomGenre("genre 2");
        genreEntityList.add(genre2);

        preferencesUtilisateur.setGenreList(genreEntityList);

        preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateur);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(preferencesUtilisateur));
    }

    @Test
    public void creerUtilisateur_should_call_save_repository_1_time() {
        // Given

        // When

        // Then
        verify(repositoryMock,times(1)).save(preferencesUtilisateur);
    }

    @Test
    public void trouverUtilisateurParId_should_return_1_element()  {
        // Given

        // When
        final PreferencesUtilisateurEntity expected = this.preferencesUtilisateurService.trouverPreferencesUtilisateurParId(1L);

        // Then
        assertThat(expected).isEqualTo(preferencesUtilisateur);
    }

    @Test
    public void trouverTousLesUtilisateurs_should_return_2_elements() {
        // Given
        PreferencesUtilisateurEntity preferencesUtilisateur2 = new PreferencesUtilisateurEntity();
        preferencesUtilisateur2.setId(2L);
        preferencesUtilisateur2.setPseudo("pseudo 2");
        preferencesUtilisateur2.setUtilisateur(this.utilisateur);

        preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateur2);

        List<PreferencesUtilisateurEntity> entities = new ArrayList<>();
        entities.add(preferencesUtilisateur);
        entities.add(preferencesUtilisateur2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<PreferencesUtilisateurEntity> trouves = this.preferencesUtilisateurService.trouverToutesLesPreferencesUtilisateurs();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverPreferencesUtilisateurParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final PreferencesUtilisateurEntity expected = this.preferencesUtilisateurService.trouverPreferencesUtilisateurParId(10L);
        });

        Assertions.assertEquals("Préférence utilisateur non trouvée", thrown.getMessage());
    }
}
