package org.epita.application.mediaDataBase.genreDataBase;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.infrastructure.mediaDataBase.GenreDataBaseRepositoryImpl;
import org.epita.infrastructure.mediaDataBase.TmdbConfig;
import org.epita.infrastructure.mediaDataBase.mapper.GenreApiMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GenreDataBaseServiceImpl.class, GenreDataBaseRepositoryImpl.class, TmdbConfig.class, GenreApiMapper.class})
public class GenreDataBaseServiceTest {

    @Autowired
    GenreDataBaseService genreDataBaseService;

    @Test
    public void rechercherLaListeDeTousLesGenres() {
        // Given

        // When
        final List<GenreEntity> trouves = this.genreDataBaseService.searchAllGenresEntity();
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des genres trouvés est de 27
        assertThat(trouves).hasSize(27);
    }

}
