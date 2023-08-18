package org.epita.exposition.mapper.selection.film;

import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.MediaDto;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.springframework.stereotype.Component;

@Component
public class FilmToMediaSelectionneCompletMapper extends Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto>{
    Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    Mapper<FilmEntity, MediaDto> mediaMapper;

    public FilmToMediaSelectionneCompletMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, Mapper<FilmEntity, MediaDto> mediaMapper) {
        this.etiquetteMapper = etiquetteMapper;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public MediaSelectionneCompletDto mapEntityToDto(FilmSelectionneEntity input) {
        return new MediaSelectionneCompletDto(
                TypeMedia.FILM,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                this.mediaMapper.mapEntityToDto((FilmEntity) input.getMediaAudioVisuelEntity()),
                input.getUtilisateurEntity().getEmail(),
                null,
                0,
                null,
                0,
                null);
    }

    @Override
    public FilmSelectionneEntity mapDtoToEntity(MediaSelectionneCompletDto input) {
        return null;
    }
}
