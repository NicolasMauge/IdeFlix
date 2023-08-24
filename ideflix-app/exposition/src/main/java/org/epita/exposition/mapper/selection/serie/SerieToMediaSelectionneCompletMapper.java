package org.epita.exposition.mapper.selection.serie;

import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.MediaDto;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.springframework.stereotype.Component;

@Component
public class SerieToMediaSelectionneCompletMapper extends Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto>{
    Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    Mapper<SerieEntity, MediaDto> mediaMapper;

    public SerieToMediaSelectionneCompletMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, Mapper<SerieEntity, MediaDto> mediaMapper) {
        this.etiquetteMapper = etiquetteMapper;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public MediaSelectionneCompletDto mapEntityToDto(SerieSelectionneeEntity input) {
        return new MediaSelectionneCompletDto(
                TypeMedia.SERIE,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                this.mediaMapper.mapEntityToDto((SerieEntity) input.getMediaAudioVisuelEntity()),
                input.getUtilisateurEntity().getEmail(),
                null,
                0,
                null,
                0,
                null);
    }

    @Override
    public SerieSelectionneeEntity mapDtoToEntity(MediaSelectionneCompletDto input) {
        return null;
    }
}
