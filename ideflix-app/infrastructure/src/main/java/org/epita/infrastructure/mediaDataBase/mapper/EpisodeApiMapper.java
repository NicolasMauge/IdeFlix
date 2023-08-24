package org.epita.infrastructure.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.EpisodeResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class EpisodeApiMapper {

    public EpisodeSerieDataBase mapEpisodeResponseDtoToEntity(EpisodeResponseDto episodeResponseDto) {


        LocalDate dateSortie;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        if (episodeResponseDto.getAir_date() != null && !episodeResponseDto.getAir_date().isEmpty()) {
            dateSortie = LocalDate.parse(episodeResponseDto.getAir_date(), dateFormatter);
        } else {
            dateSortie = LocalDate.of(1900, 1, 1); // valeur par défaut quand date inexistante
        }

        return new EpisodeSerieDataBase(
                dateSortie,
                episodeResponseDto.getCrew(),
                episodeResponseDto.getEpisode_number(),
                episodeResponseDto.getGuest_stars(),
                episodeResponseDto.getName(),
                episodeResponseDto.getOverview(),
                episodeResponseDto.getId(),
                episodeResponseDto.getProduction_code(),
                episodeResponseDto.getRuntime(),
                episodeResponseDto.getSeason_number(),
                episodeResponseDto.getStill_path(),
                episodeResponseDto.getVote_average(),
                episodeResponseDto.getVote_count());
    }
}
