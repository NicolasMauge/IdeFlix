package org.epita.application.utilisateur.preferences;

import org.epita.application.media.genre.GenreService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.infrastructure.utilisateur.PreferencesUtilisateurRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PreferencesUtilisateurServiceImpl.class })
public class PreferencesUtilisateurServiceTest {
    @Autowired
    PreferencesUtilisateurService preferencesUtilisateurService;

    @MockBean
    PreferencesUtilisateurRepository preferencesUtilisateurRepositoryMock;

    PreferencesUtilisateurEntity preferencesUtilisateur;

    @Before
    public void setUp() {
        preferencesUtilisateur = new PreferencesUtilisateurEntity();
        preferencesUtilisateur.setId(1L);
        preferencesUtilisateur.setPseudo("pseudo 1");

        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setNomGenre("genre 1");
        genreEntityList.add(genre);

        GenreEntity genre_2 = new GenreEntity();
        genre_2.setId(2L);
        genre_2.setNomGenre("genre 2");
        genreEntityList.add(genre_2);

        preferencesUtilisateur.setGenreList(genreEntityList);

        preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateur);

        when(preferencesUtilisateurRepositoryMock.findById(1L)).thenReturn(Optional.of(preferencesUtilisateur));
    }

    @Test
    public void creerUtilisateur_should_call_save_repository_1_time() {
        // Given

        // When

        // Then
        verify(preferencesUtilisateurRepositoryMock,times(1)).save(preferencesUtilisateur);
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

        preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateur2);

        List<PreferencesUtilisateurEntity> preferencesUtilisateurList = new ArrayList<>();
        preferencesUtilisateurList.add(preferencesUtilisateur);
        preferencesUtilisateurList.add(preferencesUtilisateur2);

        when(preferencesUtilisateurRepositoryMock.findAll()).thenReturn(preferencesUtilisateurList);

        // When
        final List<PreferencesUtilisateurEntity> preferencesUtilisateurEntityList = this.preferencesUtilisateurService.trouverToutesLesPreferencesUtilisateurs();

        // Then
        assertThat(preferencesUtilisateurEntityList).hasSize(2);
    }

    @Test(expected = EntityNotFoundException.class)
    public void trouverPreferencesUtilisateurParId_should_throw_exception() {
        // When
        final PreferencesUtilisateurEntity expected = this.preferencesUtilisateurService.trouverPreferencesUtilisateurParId(10L);
    }
}
