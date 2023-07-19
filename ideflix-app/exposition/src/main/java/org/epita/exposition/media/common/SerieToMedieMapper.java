package org.epita.exposition.media.common;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.media.serie.SerieDto;
import org.springframework.stereotype.Component;

@Component
public class SerieToMedieMapper extends Mapper<SerieEntity, MediaDto> {
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public SerieToMedieMapper(Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public MediaDto mapEntityToDto(SerieEntity input) {
        return new MediaDto(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                this.genreMapper.mapListEntityToDto(input.getGenreList()),
                input.getNombreSaisons());
    }

    @Override
    public SerieEntity mapDtoToEntity(MediaDto input) {
        return new SerieEntity(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                this.genreMapper.mapListDtoToEntity(input.getGenreList()),
                input.getNombreSaisons());
    }
}
