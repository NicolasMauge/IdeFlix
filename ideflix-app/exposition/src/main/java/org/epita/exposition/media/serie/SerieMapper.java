package org.epita.exposition.media.serie;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.film.FilmDto;
import org.epita.exposition.media.genre.GenreDto;
import org.springframework.stereotype.Component;

@Component
public class SerieMapper extends Mapper<SerieEntity, SerieDto> {
    Mapper<GenreEntity, GenreDto> genreMapper;

    public SerieMapper(Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public SerieDto mapEntityToDto(SerieEntity input) {
        return new SerieDto(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                genreMapper.mapListEntityToDto(input.getGenreList()),
                input.getNombreSaisons());
    }

    @Override
    public SerieEntity mapDtoToEntity(SerieDto input) {
        // TODO : risque sur la complétude de la base Genre par rapport aux données envoyées
        return new SerieEntity(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                genreMapper.mapListDtoToEntity(input.getGenreList()),
                input.getNombreSaisons());
    }
}
