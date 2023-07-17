package org.epita.application.selection.etiquette;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.infrastructure.selection.EtiquetteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EtiquetteServiceImpl.class})
public class EtiquetteServiceTest {
    @Autowired
    EtiquetteService etiquetteService;

    @MockBean
    EtiquetteRepository repositoryMock;

    EtiquetteEntity etiquette;

    @Before
    public void setUp() {
        // définition de l'étiquette utilisée pour les test
        etiquette = new EtiquetteEntity();
        etiquette.setId(1L);
        etiquette.setNomTag("tag 1");

        etiquetteService.creerEtiquette(etiquette);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(etiquette));
    }

    @Test
    public void creerFilm_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(etiquette);
    }

    @Test
    public void trouverGenreParId_shoudl_return_1_element() {
        // When
        final EtiquetteEntity expected = this.etiquetteService.trouverEtiquetteParId(1L);

        // Then
        assertThat(expected).isEqualTo(etiquette);
    }

    @Test
    public void trouverTousLesFilms_should_return_2_elements() {
        // Given
        EtiquetteEntity etiquette2 = new EtiquetteEntity();
        etiquette2.setId(2L);

        etiquetteService.creerEtiquette(etiquette2);

        List<EtiquetteEntity> entities = new ArrayList<>();

        entities.add(etiquette);
        entities.add(etiquette2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<EtiquetteEntity> trouves = this.etiquetteService.trouverToutesLesEtiquettes();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test(expected = EntityNotFoundException.class)
    public void trouverGenreParId_should_throw_exception() {
        // When
        final EtiquetteEntity expected = this.etiquetteService.trouverEtiquetteParId(10L);
    }
}
