package org.epita.application.mediaDataBase.genreDataBase;

import org.epita.domaine.media.GenreEntity;
import org.epita.infrastructure.mediaDataBase.GenreDataBaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GenreDataBaseServiceImpl.class})
public class GenreDataBaseServiceTest {

    @Autowired
    GenreDataBaseService genreDataBaseService;

    @MockBean
    GenreDataBaseRepository genreRepository;

    @BeforeEach
    public void setUp() {
        List<GenreEntity> genreEntityListMovie = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setIdTmdb("1");
        genre.setNomGenre("genre 1");
        genreEntityListMovie.add(genre);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setIdTmdb("2");
        genre2.setNomGenre("genre 2");
        genreEntityListMovie.add(genre2);

        List<GenreEntity> genreEntityListSerie = new ArrayList<>();
        GenreEntity genre3 = new GenreEntity();
        genre3.setId(3L);
        genre3.setIdTmdb("3");
        genre3.setNomGenre("genre 3");
        genreEntityListSerie.add(genre);
        genreEntityListSerie.add(genre3);

        when(genreRepository.searchAllGenresEntityForMovie()).thenReturn(genreEntityListMovie);
        when(genreRepository.searchAllGenresEntityForTV()).thenReturn(genreEntityListSerie);
    }

    @Test
    public void rechercherLaListeDeTousLesGenres() {
        // Given  voir @BeforeEach

        // When
        final List<GenreEntity> trouves = this.genreDataBaseService.searchAllGenresEntity();
        System.out.println("trouves: " + trouves.get(0).getId());

        // Then
        //le tableau des genres trouvés est de 3
        assertThat(trouves).hasSize(3);
    }

}
