package org.epita.exposition.media.film;

import org.epita.application.media.genre.GenreService;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmMapper extends Mapper<FilmEntity, FilmDto> {
    private Mapper<GenreEntity, GenreDto> genreMapper;
    private GenreService genreService;

    public FilmMapper(Mapper<GenreEntity, GenreDto> genreMapper, GenreService genreService) {
        this.genreMapper = genreMapper;
        this.genreService = genreService;
    }

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
        // TODO : risque sur la complétude de la base Genre par rapport aux données envoyées
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
