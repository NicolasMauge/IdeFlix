package org.epita.application.mediaDataBase.serieDataBase;


import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.infrastructure.mediaDataBase.EpisodeDataBaseRepositoryImpl;
import org.epita.infrastructure.mediaDataBase.TmdbConfig;
import org.epita.infrastructure.mediaDataBase.mapper.EpisodeApiMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EpisodeDataBaseServiceImpl.class, EpisodeDataBaseRepositoryImpl.class, TmdbConfig.class, EpisodeApiMapper.class})
public class EpisodeDataBaseServiceTest {

    @Autowired
    EpisodeDataBaseService episodeDataBaseService;

    @Test
    public void recherche_Episode_1_Saison_8_DeLaSerie_1408() {
        // Given
        long id = 1408;
        int numberEpisode = 1;
        int numberSeason = 8;

        // When
        final EpisodeSerieDataBase trouves = this.episodeDataBaseService.findEpisodeByNumberEpisodeAndNumberSeasonAndId(id, numberSeason, numberEpisode);
        System.out.println("trouves: " + trouves);

        // Then
        //le titre de l'épisode est
        assertThat(trouves.getName()).isEqualTo("5 jours à tirer");
    }
}
