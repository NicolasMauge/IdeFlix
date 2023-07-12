package org.epita.exposition.media.film;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmMapper extends Mapper<FilmEntity, FilmDto> {
    Mapper<GenreEntity, GenreDto> genreMapper;

    @Override
    public FilmDto mapEntityToDto(FilmEntity input) {
        return new FilmDto(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                genreMapper.mapListEntityToDto(input.getGenreList()));
    }

    @Override
    public FilmEntity mapDtoToEntity(FilmDto input) {
        return new FilmEntity(
                input.getIdTmdb(),
                input.getTitre(),
                input.getDateSortie(),
                input.getDuree(),
                input.getCheminAffichePortrait(),
                input.getCheminAffichePaysage(),
                input.getNoteTmdb(),
                genreMapper.mapListDtoToEntity(input.getGenreList()));
    }
}
