package org.epita.application.selection.serieselectionnee;

import org.epita.application.media.serie.SerieServiceImpl;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.media.GenreRepository;
import org.epita.infrastructure.media.SerieRepository;
import org.epita.infrastructure.selection.SerieSelectionneeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
@SpringBootTest(classes = {SerieSelectionneeServiceImpl.class, SerieServiceImpl.class})
public class SerieSelectionneeServiceTest {
    @Autowired
    SerieSelectionneeService serieSelectionneeService;

    @MockBean
    SerieSelectionneeRepository repositoryMock;

    @MockBean
    SerieRepository serieRepository;

    @MockBean
    GenreRepository genreRepository;

    SerieSelectionneeEntity serieSelectionnee;

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

        // définition série
        SerieEntity serie = new SerieEntity();
        serie.setIdTmdb("IdTmdb");

        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        // définition de la série sélectionnée utilisée pour les test
        serieSelectionnee = new SerieSelectionneeEntity();
        serieSelectionnee.setId(1L);
        serieSelectionnee.setEtiquetteEntityList(entityList);
        serieSelectionnee.setUtilisateurEntity(utilisateur);
        serieSelectionnee.setMediaAudioVisuelEntity(serie);

        serieSelectionneeService.creerSerieSelectionnee(serieSelectionnee);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(serieSelectionnee));
        when(repositoryMock.findByUtilisateurEntity(utilisateur)).thenReturn(List.of(serieSelectionnee));
        when(repositoryMock.findSerieSelectionneeEntitiesByUtilisateurEntityEmailIs(utilisateur.getEmail())).thenReturn(List.of(serieSelectionnee));
    }

    @Test
    public void creerSerieSelectionnee_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(serieSelectionnee);
    }

    @Test
    public void trouverSerieSelectionneeParId_shoudl_return_1_element() {
        // When
        final SerieSelectionneeEntity expected = this.serieSelectionneeService.trouverSerieSelectionneeParId(1L);

        // Then
        assertThat(expected).isEqualTo(serieSelectionnee);
    }

    @Test
    public void trouverToutesLesSeriesSelectionnees_should_return_2_elements() {
        // Given
        // définition série
        SerieEntity serie = new SerieEntity();
        serie.setIdTmdb("IdTmdb");

        SerieSelectionneeEntity serieSelectionnee2 = new SerieSelectionneeEntity();
        serieSelectionnee2.setId(2L);
        serieSelectionnee2.setMediaAudioVisuelEntity(serie);
        serieSelectionnee2.setUtilisateurEntity(this.utilisateur);

        serieSelectionneeService.creerSerieSelectionnee(serieSelectionnee2);

        List<SerieSelectionneeEntity> entities = new ArrayList<>();

        entities.add(serieSelectionnee);
        entities.add(serieSelectionnee2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<SerieSelectionneeEntity> trouves = this.serieSelectionneeService.trouverToutesLesSeriesSelectionnees();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverSerieSelectionneeParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final SerieSelectionneeEntity expected = this.serieSelectionneeService.trouverSerieSelectionneeParId(10L);
        });

        Assertions.assertEquals("Série sélectionnée non trouvée", thrown.getMessage());
    }

    @Test
    public void trouverSerieSelectionneeParUtilisateur_should_return_1_element() {
        // When
        final List<SerieSelectionneeEntity> expected = this.serieSelectionneeService.trouverSerieParUtilisateur(utilisateur);

        // Then
        assertThat(expected).isEqualTo(List.of(serieSelectionnee));
    }

    @Test
    public void trouverFilmsSelectionnesParEmailUtilisateur_should_return_1_element() {
        // When
        final List<SerieSelectionneeEntity> expected = this.serieSelectionneeService.trouverSeriesSelectionneesParEmailUtilisateur("test@test.com");

        // Then
        assertThat(expected).isEqualTo(List.of(serieSelectionnee));
    }
}
