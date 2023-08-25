package org.epita.application.media.serie;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.infrastructure.media.SerieRepository;
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
@SpringBootTest(classes = {SerieServiceImpl.class})
public class SerieServiceTest {
    @Autowired
    SerieService serieService;

    @MockBean
    SerieRepository repositoryMock;

    SerieEntity serie;

    @BeforeEach
    public void setUp() {
        // définition de genres
        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setNomGenre("genre 1");
        genreEntityList.add(genre);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setNomGenre("genre 2");
        genreEntityList.add(genre2);

        // définition du film utilisé pour les test
        serie = new SerieEntity();
        serie.setId(1L);
        serie.setGenreList(genreEntityList);

        serieService.creerSerie(serie);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(serie));
    }

    @Test
    public void creerSerie_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(serie);
    }

    @Test
    public void trouverSerieParId_shoudl_return_1_element() {
        // When
        final SerieEntity expected = this.serieService.trouverSerieParId(1L);

        // Then
        assertThat(expected).isEqualTo(serie);
    }

    @Test
    public void trouverToutesLesSeries_should_return_2_elements() {
        // Given
        SerieEntity serie2 = new SerieEntity();
        serie2.setId(2L);

        GenreEntity genre = new GenreEntity();
        genre.setId(1L);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);

        serieService.creerSerie(serie2);

        List<SerieEntity> entities = new ArrayList<>();

        entities.add(serie);
        entities.add(serie2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<SerieEntity> trouves = this.serieService.trouverToutesLesSeries();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverSerieParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final SerieEntity expected = this.serieService.trouverSerieParId(10L);
        });

        Assertions.assertEquals("Série non trouvée", thrown.getMessage());
    }
}
