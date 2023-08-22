package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SaisonSerieDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.SaisonDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.SerieDataBaseResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class SerieWithSaisonDataBaseMapper extends Mapper<SerieDataBase, SerieDataBaseResponseDto> {


    private Mapper<GenreDataBase, GenreDataBaseResponseDto> genreDataBaseMapper;

    private Mapper<SaisonSerieDataBase, SaisonDataBaseResponseDto> saisonDataBaseMapper;


    public SerieWithSaisonDataBaseMapper(Mapper<GenreDataBase, GenreDataBaseResponseDto> genreDataBaseMapper,
                                         Mapper<SaisonSerieDataBase, SaisonDataBaseResponseDto> saisonDataBaseMapper) {
        this.genreDataBaseMapper = genreDataBaseMapper;
        this.saisonDataBaseMapper = saisonDataBaseMapper;
    }

    @Override
    public SerieDataBaseResponseDto mapEntityToDto(SerieDataBase input) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = input.getDateSortie() != null && !input.getDateSortie().equals(LocalDate.of(1900, 1 , 1))?
                input.getDateSortie().format(dateFormatter) : "Date inconnue";


        return new SerieDataBaseResponseDto(
                input.getIdDataBase(),
                input.getTitre(),
                formattedDate,
                input.getDuree(),
                input.getResume(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteDataBase(),
                this.genreDataBaseMapper.mapListEntityToDto(input.getGenres()),
                TypeMedia.SERIE,
                input.getNombreSaisons(),
                this.saisonDataBaseMapper.mapListEntityToDto(input.getListeSaisons())
        );

    }

    @Override
    public SerieDataBase mapDtoToEntity(SerieDataBaseResponseDto input) {
        return null;
    }
}
