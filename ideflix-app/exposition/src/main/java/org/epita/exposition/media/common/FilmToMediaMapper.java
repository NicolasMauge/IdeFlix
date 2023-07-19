package org.epita.exposition.media.common;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreDto;
import org.epita.exposition.selection.etiquette.EtiquetteDto;
import org.springframework.stereotype.Component;

@Component
public class FilmToMediaMapper extends Mapper<FilmEntity, MediaDto> {
    private Mapper<GenreEntity, GenreDto> genreMapper;

    public FilmToMediaMapper(Mapper<GenreEntity, GenreDto> genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Override
    public MediaDto mapEntityToDto(FilmEntity input) {
        return new MediaDto(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                this.genreMapper.mapListEntityToDto(input.getGenreList()),
                0);
    }

    @Override
    public FilmEntity mapDtoToEntity(MediaDto input) {
        return new FilmEntity(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                this.genreMapper.mapListDtoToEntity(input.getGenreList())
        );
    }
}
