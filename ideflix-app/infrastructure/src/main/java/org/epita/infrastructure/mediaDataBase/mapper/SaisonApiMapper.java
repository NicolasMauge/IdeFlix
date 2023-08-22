package org.epita.infrastructure.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.SaisonSerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.SaisonResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SaisonApiMapper {

//    public static List<GenreDataBase> mapFromGenreResponseDtoList(List<GenreResponseDto> genreResponseDtos) {
//        List<GenreDataBase> genreDataBases = new ArrayList<>();
//        for (GenreResponseDto genreResponseDto : genreResponseDtos) {
//            GenreDataBase genreDataBase = new GenreDataBase(genreResponseDto.getId(), genreResponseDto.getName());
//            genreDataBases.add(genreDataBase);
//        }
//        return genreDataBases;
//    }

    public static List<SaisonSerieDataBase> mapFromSaisonResponseDtoList(List<SaisonResponseDto> saisonResponseDtos) {
        List<SaisonSerieDataBase> saisonSerieDataBases = new ArrayList<>();


        for (SaisonResponseDto saisonResponseDto : saisonResponseDtos) {

            LocalDate dateSortie;
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (saisonResponseDto.getAir_date() != null && !saisonResponseDto.getAir_date().isEmpty()) {
                dateSortie = LocalDate.parse(saisonResponseDto.getAir_date(), dateFormatter);
                System.out.println("date sortie saison: " + dateSortie);
            } else {
                dateSortie = LocalDate.of(1900, 1, 1); // valeur par défaut quand date inexistante
            }

            SaisonSerieDataBase saisonSerieDataBase = new SaisonSerieDataBase(
                    dateSortie,
                    saisonResponseDto.getEpisode_count(),
                    saisonResponseDto.getId(),
                    saisonResponseDto.getName(),
                    saisonResponseDto.getOverview(),
                    saisonResponseDto.getPoster_path(),
                    saisonResponseDto.getSeason_number(),
                    saisonResponseDto.getVote_average()
            );
            saisonSerieDataBases.add(saisonSerieDataBase);
        }
        return saisonSerieDataBases;
    }
}
