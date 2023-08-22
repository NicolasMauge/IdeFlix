package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SerieDataBaseMapper.class})
@ContextConfiguration(classes = {GenreDataBaseMapper.class})
public class SerieDataBaseMapperTest {

    @Autowired
    private Mapper<GenreDataBase, GenreDataBaseResponseDto> mapperGenre;


    @Autowired
    private SerieDataBaseMapper serieDataBaseMapper;


    @Test
    public void should_return_mapEntityToDto() {
        //Given

        SerieDataBase serie = new SerieDataBase();

        serie.setIdDataBase(1408L);
        serie.setDateSortie(LocalDate.of(2004, 11,16));
        serie.setDuree(0);
        serie.setNoteDataBase(8.6F);
        serie.setTitre("Dr House");
        serie.setCheminAffichePaysage("/r0Q6eeN9L1ORL9QsV0Sg8ZV3vnv.jpg");
        serie.setCheminAffichePortrait("/2Bw00vWztWFeTFpWTuPbUMkyDCA.jpg");
        serie.setResume("Le docteur Gregory House, est un brillant médecin à tendance misanthrope qui dirige une équipe d'internistes au sein de l'hôpital fictif de Princeton-Plainsboro dans le New Jersey. House est un personnage arrogant, cynique, anticonformiste et asocial. Il souffre d'une claudication provenant d'une douleur chronique à la jambe droite due à un infarctus du muscle de la cuisse. Il marche avec une canne et abuse de Vicodin, un analgésique opiacé, pour soulager sa douleur.");
        List<GenreDataBase> genresSerie = new ArrayList<>();
        genresSerie.add(0, new GenreDataBase(18, ""));
        genresSerie.add(1, new GenreDataBase(9648, ""));
        serie.setGenres(genresSerie);

        //when
        MediaDataBaseResponseDto mediaDto = this.serieDataBaseMapper.mapEntityToDto(serie);

        System.out.println("serie: " + serie.getIdDataBase());
        System.out.println("serieDto: " + mediaDto.getIdDataBase());

        //then
        assertThat(mediaDto.getIdDataBase()).isEqualTo(serie.getIdDataBase());
        assertThat(mediaDto.getTitre()).isEqualTo(serie.getTitre());
        assertThat(mediaDto.getDateSortie()).isEqualTo(serie.getDateSortie().toString()); // Assuming dateSortie is LocalDate
        assertThat(mediaDto.getDuree()).isEqualTo(serie.getDuree());
        assertThat(mediaDto.getResume()).isEqualTo(serie.getResume());
        assertThat(mediaDto.getCheminAffichePortrait()).isEqualTo(serie.getCheminAffichePortrait());
        assertThat(mediaDto.getCheminAffichePaysage()).isEqualTo(serie.getCheminAffichePaysage());
        assertThat(mediaDto.getNoteDataBase()).isEqualTo(serie.getNoteDataBase());

        // Assertion pour les genres
//        assertThat(mediaDto.getGenreDataBaseResponseDtos())
//                .extracting("idDataBase", "nomGenre")
//                .containsExactly(
//                        tuple(35, "Comedie"),
//                        tuple(18, "Drame"),
//                        tuple(10749, "Romance")
//                );
    }
}
