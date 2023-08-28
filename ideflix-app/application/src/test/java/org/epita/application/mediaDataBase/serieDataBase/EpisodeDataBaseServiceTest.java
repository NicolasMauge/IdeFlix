package org.epita.application.mediaDataBase.serieDataBase;


import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.infrastructure.mediaDataBase.EpisodeDataBaseRepository;
import org.epita.infrastructure.mediaDataBase.EpisodeDataBaseRepositoryImpl;
import org.epita.infrastructure.mediaDataBase.TmdbConfig;
import org.epita.infrastructure.mediaDataBase.mapper.EpisodeApiMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EpisodeDataBaseServiceImpl.class})
public class EpisodeDataBaseServiceTest {

    @Autowired
    EpisodeDataBaseService episodeDataBaseService;

    @MockBean
    EpisodeDataBaseRepository episodeDataBaseRepositoryMock;


    @Test
    public void recherche_Episode_1_Saison_8_DeLaSerie_1408() {
        // Given
        long id = 1408;
        int numberEpisode = 1;
        int numberSeason = 8;

        EpisodeSerieDataBase episode1 = new EpisodeSerieDataBase();
        episode1.setAir_date(LocalDate.of(2004,11,16));
        episode1.setCrew(null);
        episode1.setEpisode_number(1);
        episode1.setGuest_stars(null);
        episode1.setName("5 jours à tirer");
        episode1.setOverview("Resume saison 8");
        episode1.setId(1408);
        episode1.setProduction_code("");
        episode1.setRuntime(0);
        episode1.setSeason_number(8);
        episode1.setStill_path("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
        episode1.setVote_average(8.6F);
        episode1.setVote_count(0);

        when(episodeDataBaseRepositoryMock.searchEpisodeByIdSeriesAndNumberSeasonAndEpisodeNumber(id, numberSeason, numberEpisode)).thenReturn(episode1);

        // When
        final EpisodeSerieDataBase trouves = this.episodeDataBaseService.findEpisodeByNumberEpisodeAndNumberSeasonAndId(id, numberSeason, numberEpisode);
        System.out.println("trouves: " + trouves);

        // Then
        //le titre de l'épisode est
        assertThat(trouves.getName()).isEqualTo("5 jours à tirer");
    }
}
