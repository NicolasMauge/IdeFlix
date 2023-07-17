package org.epita.application.media.film;

import org.epita.application.media.genre.GenreService;
import org.epita.application.media.genre.GenreServiceImpl;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.infrastructure.media.FilmRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FilmServiceImpl.class})
public class FilmServiceTest {
    @Autowired
    FilmService filmService;

    @MockBean
    FilmRepository repositoryMock;

    FilmEntity film;

    @Before
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
    public void trouverGenreParId_shoudl_return_1_element() {
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

    @Test(expected = EntityNotFoundException.class)
    public void trouverGenreParId_should_throw_exception() {
        // When
        final FilmEntity expected = this.filmService.trouverFilmParId(10L);
    }
}
