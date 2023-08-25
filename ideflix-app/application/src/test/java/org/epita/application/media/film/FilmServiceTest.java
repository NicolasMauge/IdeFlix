package org.epita.application.media.film;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.infrastructure.media.FilmRepository;
import org.epita.infrastructure.media.GenreRepository;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FilmServiceImpl.class})
public class FilmServiceTest {
    @Autowired
    FilmService filmService;

    @MockBean
    FilmRepository repositoryMock;

    @MockBean
    GenreRepository genreRepository;

    FilmEntity film;

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
        film = new FilmEntity();
        film.setId(1L);
        film.setGenreList(genreEntityList);

        filmService.creerFilm(film);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(film));
    }

    @Test
    public void creerFilm_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(film);
    }

    @Test
    public void trouverFilmParId_should_return_1_element() {
        // When
        final FilmEntity expected = this.filmService.trouverFilmParId(1L);

        // Then
        assertThat(expected).isEqualTo(film);
    }

    @Test
    public void trouverTousLesFilms_should_return_2_elements() {
        // Given
        FilmEntity film2 = new FilmEntity();
        film2.setId(2L);

        filmService.creerFilm(film2);

        List<FilmEntity> entities = new ArrayList<>();

        entities.add(film);
        entities.add(film2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<FilmEntity> trouves = this.filmService.trouverTousLesFilms();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverFilmParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final FilmEntity expected = this.filmService.trouverFilmParId(10L);
        });

        Assertions.assertEquals("Film non trouvé", thrown.getMessage());
    }
}
