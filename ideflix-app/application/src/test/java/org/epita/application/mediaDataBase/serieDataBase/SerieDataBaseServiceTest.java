package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.SerieDataBaseRepositoryImpl;
import org.epita.infrastructure.mediaDataBase.TmdbConfig;
import org.epita.infrastructure.mediaDataBase.mapper.SerieApiMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SerieDataBaseServiceImpl.class, SerieDataBaseRepositoryImpl.class, TmdbConfig.class, SerieApiMapper.class})
public class SerieDataBaseServiceTest {

    @Autowired
    SerieDataBaseService serieDataBaseService;

    @Test
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_bienvenuechezleschtis() {
        // Given
        String query = "bienvenue%20chez%20les%20ch%27tis";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des films trouvés est de 1
        assertThat(trouves).hasSize(0);
    }

    @Test
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_iletaitunefois() {
        // Given
        String query = "il%20%C3%A9tait%20une%20fois";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des films trouvés est de 20 (2 pages en réalité et 38 results au total)
        assertThat(trouves).hasSize(20);
    }

    @Test
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_drHouse() {
        // Given
        String query = "dr%20house";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des films trouvés est de 4
        assertThat(trouves).hasSize(4);
    }
}
