package org.epita.exposition.mapper.media.serie;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.MediaDto;
import org.epita.exposition.dto.media.GenreDto;
import org.springframework.stereotype.Component;

@Component
public class SerieToMediaMapper extends Mapper<SerieEntity, MediaDto> {
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public SerieToMediaMapper(Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public MediaDto mapEntityToDto(SerieEntity input) {
        return new MediaDto(
                input.getIdTmdb(),
                TypeMedia.SERIE,
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
