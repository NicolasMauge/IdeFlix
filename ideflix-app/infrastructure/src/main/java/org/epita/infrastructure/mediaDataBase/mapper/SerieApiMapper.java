package org.epita.infrastructure.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.apidto.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SerieApiMapper {

    public SerieDataBase mapSerieLightResponseDtoToEntity(SerieLightResponseDto serieLightResponseDto) {

        int duree = 0;

        int nombreSaisons = 0;

        List<GenreDataBase> listGenres = new ArrayList<>();
        for (int i = 0; i < serieLightResponseDto.getGenre_ids().size(); i++) {
            listGenres.add(i,new GenreDataBase(serieLightResponseDto.getGenre_ids().get(i),""));
        }

        LocalDate dateSortie;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (serieLightResponseDto.getFirst_air_date() != null && !serieLightResponseDto.getFirst_air_date().isEmpty()) {
            dateSortie = LocalDate.parse(serieLightResponseDto.getFirst_air_date(), dateFormatter);
        } else {
            dateSortie = LocalDate.of(1900,01,01); // valeur par défaut quand date inexistante
        }

        return new SerieDataBase(serieLightResponseDto.getId(),
                serieLightResponseDto.getName(),
                listGenres,
                serieLightResponseDto.getOverview(),
                serieLightResponseDto.getPoster_path(),
                serieLightResponseDto.getBackdrop_path(),
                dateSortie,
                duree,
                serieLightResponseDto.getVote_average(),
                nombreSaisons);
    };

    public List<SerieDataBase> mapSearchSeriesResponseDtoToEntityList(SearchSeriesResponseDto searchSeriesResponseDto) {

        List<SerieDataBase> serieDataBaseList = new ArrayList<>();

        for (SerieLightResponseDto serieLightResponseDto : searchSeriesResponseDto.getResults()) {
            serieDataBaseList.add(mapSerieLightResponseDtoToEntity(serieLightResponseDto));
        }

        return serieDataBaseList;
    };
}
