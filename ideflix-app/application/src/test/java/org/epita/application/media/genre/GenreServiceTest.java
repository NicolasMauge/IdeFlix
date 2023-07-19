package org.epita.application.media.genre;


import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.infrastructure.media.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GenreServiceImpl.class})
public class GenreServiceTest {
    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository repositoryMock;

    private GenreEntity genre;

    @BeforeEach
    public void setUp() {
        genre = new GenreEntity();
        genre.setId(1L);
        genre.setNomGenre("genre 1");

        genreService.creerGenre(genre);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(genre));
    }

    @Test
    public void creerGenre_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(genre);
    }

    @Test
    public void trouverGenreParId_shoudl_return_1_element() {
        // When
        final GenreEntity expected = this.genreService.trouverGenreParId(1L);

        // Then
        assertThat(expected).isEqualTo(genre);
    }

    @Test
    public void trouverTousLesGenres_should_return_2_elements() {
        // Given
        GenreEntity genre2 = new GenreEntity();
        genre2.setId(1L);
        genre2.setNomGenre("genre 2");

        genreService.creerGenre(genre2);

        List<GenreEntity> entities = new ArrayList<>();

        entities.add(genre);
        entities.add(genre2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<GenreEntity> trouves = this.genreService.trouverTousLesGenres();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverGenreParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final GenreEntity expected = this.genreService.trouverGenreParId(10L);
        });

        Assertions.assertEquals("Genre non trouvé", thrown.getMessage());

    }
}
