package org.epita.application.selection.filmselectionne;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.infrastructure.selection.FilmSelectionneRepository;
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
@SpringBootTest(classes = {FilmSelectionneServiceImpl.class})
public class FilmSelectionneeServiceTest {
    @Autowired
    FilmSelectionneService filmSelectionneService;

    @MockBean
    FilmSelectionneRepository repositoryMock;

    FilmSelectionneEntity filmSelectionne;

    @Before
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

        // définition du film sélectionné utilisé pour les test
        filmSelectionne = new FilmSelectionneEntity();
        filmSelectionne.setId(1L);
        filmSelectionne.setEtiquetteEntityList(entityList);

        filmSelectionneService.creerFilmSelectionne(filmSelectionne);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(filmSelectionne));
    }

    @Test
    public void creerFilm_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(filmSelectionne);
    }

    @Test
    public void trouverGenreParId_shoudl_return_1_element() {
        // When
        final FilmSelectionneEntity expected = this.filmSelectionneService.trouverFilmSelectionneParId(1L);

        // Then
        assertThat(expected).isEqualTo(filmSelectionne);
    }

    @Test
    public void trouverTousLesFilms_should_return_2_elements() {
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

    @Test(expected = EntityNotFoundException.class)
    public void trouverGenreParId_should_throw_exception() {
        // When
        final FilmSelectionneEntity expected = this.filmSelectionneService.trouverFilmSelectionneParId(10L);
    }
}
