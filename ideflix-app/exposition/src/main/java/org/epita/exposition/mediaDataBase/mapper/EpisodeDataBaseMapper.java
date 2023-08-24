package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.EpisodeSerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.EpisodeDataBaseResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class EpisodeDataBaseMapper extends Mapper<EpisodeSerieDataBase, EpisodeDataBaseResponseDto> {

    @Override
    public EpisodeDataBaseResponseDto mapEntityToDto(EpisodeSerieDataBase input) {


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = input.getAir_date() != null && !input.getAir_date().equals(LocalDate.of(1900, 1 , 1))?
                input.getAir_date().format(dateFormatter) : "Date inconnue";

        return new EpisodeDataBaseResponseDto(
                formattedDate,
                input.getEpisode_number(),
                input.getRuntime(),
                input.getId(),
                input.getName(),
                input.getOverview(),
                input.getStill_path(),
                input.getSeason_number(),
                input.getVote_average());

    }

    @Override
    public EpisodeSerieDataBase mapDtoToEntity(EpisodeDataBaseResponseDto input) {
        return null;
    }
}
