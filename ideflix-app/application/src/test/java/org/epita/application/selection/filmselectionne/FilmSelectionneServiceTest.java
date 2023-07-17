package org.epita.application.selection.filmselectionne;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.selection.FilmSelectionneRepository;
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
@SpringBootTest(classes = {FilmSelectionneServiceImpl.class})
public class FilmSelectionneServiceTest {
    @Autowired
    FilmSelectionneService filmSelectionneService;

    @MockBean
    FilmSelectionneRepository repositoryMock;

    FilmSelectionneEntity filmSelectionne;

    UtilisateurEntity utilisateur;

    @BeforeEach
    public void setUp() {
        // définition des étiquettes
        EtiquetteEntity etiquette = new EtiquetteEntity();
        etiquette.setId(1L);
        etiquette.setNomTag("tag 1");

        EtiquetteEntity etiquette2 = new EtiquetteEntity();
        etiquette2.setId(2L);
        etiquette2.setNomTag("tag 2");

        List<EtiquetteEntity> entityList = new ArrayList<>();
        entityList.add(etiquette);
        entityList.add(etiquette2);

        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        // définition du film sélectionné utilisé pour les test
        filmSelectionne = new FilmSelectionneEntity();
        filmSelectionne.setId(1L);
        filmSelectionne.setEtiquetteEntityList(entityList);
        filmSelectionne.setUtilisateurEntity(utilisateur);

        filmSelectionneService.creerFilmSelectionne(filmSelectionne);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(filmSelectionne));
        when(repositoryMock.findByUtilisateurEntity(utilisateur)).thenReturn(List.of(filmSelectionne));
    }

    @Test
    public void creerFilm_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(filmSelectionne);
    }

    @Test
    public void trouverFilmSelectionneParId_shoudl_return_1_element() {
        // When
        final FilmSelectionneEntity expected = this.filmSelectionneService.trouverFilmSelectionneParId(1L);

        // Then
        assertThat(expected).isEqualTo(filmSelectionne);
    }

    @Test
    public void trouverTousLesFilmsSelectionnes_should_return_2_elements() {
        // Given
        FilmSelectionneEntity filmSelectionne2 = new FilmSelectionneEntity();
        filmSelectionne2.setId(2L);

        filmSelectionneService.creerFilmSelectionne(filmSelectionne2);

        List<FilmSelectionneEntity> entities = new ArrayList<>();

        entities.add(filmSelectionne);
        entities.add(filmSelectionne2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<FilmSelectionneEntity> trouves = this.filmSelectionneService.trouverTousLesFilmsSelectionnes();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverFilmSelectionneParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final FilmSelectionneEntity expected = this.filmSelectionneService.trouverFilmSelectionneParId(10L);
        });

        Assertions.assertEquals("Film sélectionné non trouvé", thrown.getMessage());
    }

    @Test
    public void trouverFilmSelectionneParUtilisateur_should_return_1_element() {
        // When
        final List<FilmSelectionneEntity> expected = this.filmSelectionneService.trouverFilmSelectionneeParUtilisateur(utilisateur);

        // Then
        assertThat(expected).isEqualTo(List.of(filmSelectionne));
    }
}
