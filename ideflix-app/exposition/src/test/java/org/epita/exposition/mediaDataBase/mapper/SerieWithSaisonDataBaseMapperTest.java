package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SaisonSerieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.SaisonDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.SerieDataBaseResponseDto;
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
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SerieWithSaisonDataBaseMapper.class})
@ContextConfiguration(classes = {GenreDataBaseMapper.class, SaisonDataBaseMapper.class, SerieWithSaisonDataBaseMapperTest.class})
public class SerieWithSaisonDataBaseMapperTest {

    @Autowired
    private Mapper<GenreDataBase, GenreDataBaseResponseDto> mapperGenre;

    @Autowired
    private Mapper<SaisonSerieDataBase, SaisonDataBaseResponseDto> mapperSaison;


    @Autowired
    private SerieWithSaisonDataBaseMapper serieWithSaisonDataBaseMapper;

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
        genresSerie.add(0, new GenreDataBase(18, "Drame"));
        genresSerie.add(1, new GenreDataBase(9648, "Mystère"));
        serie.setGenres(genresSerie);
        serie.setNombreSaisons(8);

        List<SaisonSerieDataBase> saisons = new ArrayList<>();
        saisons.add(0,new SaisonSerieDataBase(LocalDate.of(2011, 2,10), 40, 3682, "Episodes spéciaux", "", "/7h6wLeZwTWDCjF67nsE8DoAhtuj.jpg",0,0 ));
        serie.setListeSaisons(saisons);

        //when
        SerieDataBaseResponseDto serieDto = this.serieWithSaisonDataBaseMapper.mapEntityToDto(serie);

        System.out.println("serie: " + serie.getIdDataBase());
        System.out.println("serieDto: " + serieDto.getIdDataBase());

        //then
        assertThat(serieDto.getIdDataBase()).isEqualTo(serie.getIdDataBase());
        assertThat(serieDto.getTitre()).isEqualTo(serie.getTitre());
        assertThat(serieDto.getDateSortie()).isEqualTo(serie.getDateSortie().toString()); // Assuming dateSortie is LocalDate
        assertThat(serieDto.getDuree()).isEqualTo(serie.getDuree());
        assertThat(serieDto.getResume()).isEqualTo(serie.getResume());
        assertThat(serieDto.getCheminAffichePortrait()).isEqualTo(serie.getCheminAffichePortrait());
        assertThat(serieDto.getCheminAffichePaysage()).isEqualTo(serie.getCheminAffichePaysage());
        assertThat(serieDto.getNoteDataBase()).isEqualTo(serie.getNoteDataBase());

        // Assertion pour les genres
        assertThat(serieDto.getGenreDataBaseResponseDtos())
                .extracting("idDataBase", "nomGenre")
                .containsExactly(
                        tuple(18,"Drame"),
                        tuple(9648, "Mystère")
                );

        // Assertion pour les saisons
        assertThat(serieDto.getSaisonDataBaseResponseDtos())
                .extracting("idDataBase")
                .containsExactly(3682L);

    }
}
