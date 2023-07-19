package org.epita.exposition.mapper.selection.serie;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.common.EmailNotFoundInJson;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.MediaDto;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.springframework.stereotype.Component;

@Component
public class SerieToMediaSelectionneMapper extends Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> {
    Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    Mapper<SerieEntity, MediaDto> serieMapper;
    UtilisateurService utilisateurService;

    public SerieToMediaSelectionneMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, Mapper<SerieEntity, MediaDto> serieMapper, UtilisateurService utilisateurService) {
        this.etiquetteMapper = etiquetteMapper;
        this.serieMapper = serieMapper;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public MediaSelectionneCompletDto mapEntityToDto(SerieSelectionneeEntity input) {
        return new MediaSelectionneCompletDto(
                input.getId(),
                TypeMedia.SERIE,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                this.serieMapper.mapEntityToDto((SerieEntity) input.getMediaAudioVisuelEntity()),
                input.getUtilisateurEntity().getEmail(),
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode());
    }

    @Override
    public SerieSelectionneeEntity mapDtoToEntity(MediaSelectionneCompletDto input) {
        if(input.getEmail()==null) {
            throw new EmailNotFoundInJson("Champ email non trouvé dans le json");
        }

        UtilisateurEntity utilisateur = utilisateurService.trouverUtilisateurParEmail(input.getEmail());

        return new SerieSelectionneeEntity(
                input.getId(),
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                this.serieMapper.mapDtoToEntity(input.getMedia()),
                utilisateur,
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode());
    }
}
