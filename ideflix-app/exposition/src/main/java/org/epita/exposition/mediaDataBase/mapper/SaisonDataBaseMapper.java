package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.SaisonSerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.SaisonDataBaseResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SaisonDataBaseMapper extends Mapper<SaisonSerieDataBase, SaisonDataBaseResponseDto> {
    @Override
    public SaisonDataBaseResponseDto mapEntityToDto(SaisonSerieDataBase input) {
        return new SaisonDataBaseResponseDto(
                input.getAir_date(),
                input.getEpisode_count(),
                input.getId(),
                input.getName(),
                input.getOverview(),
                input.getPoster_path(),
                input.getSeason_number(),
                input.getVote_average()
                );
    }

    @Override
    public SaisonSerieDataBase mapDtoToEntity(SaisonDataBaseResponseDto input) {
        return new SaisonSerieDataBase(
                input.getDateSortie(),
                input.getNombreEpisodes(),
                input.getIdDataBase(),
                input.getTitre(),
                input.getResume(),
                input.getCheminAffichePortrait(),
                input.getNumeroSaison(),
                input.getNoteDataBase()
                );
    }
}
