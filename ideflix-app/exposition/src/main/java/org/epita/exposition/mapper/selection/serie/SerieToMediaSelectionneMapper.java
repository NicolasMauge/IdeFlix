package org.epita.exposition.mapper.selection.serie;

import org.epita.application.media.serie.SerieService;
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
import org.epita.exposition.dto.selection.MediaSelectionneDto;
import org.springframework.stereotype.Component;

@Component
public class SerieToMediaSelectionneMapper extends Mapper<SerieSelectionneeEntity, MediaSelectionneDto> {
    Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    Mapper<SerieEntity, MediaDto> serieMapper;
    UtilisateurService utilisateurService;
    SerieService serieService;

    public SerieToMediaSelectionneMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, Mapper<SerieEntity, MediaDto> serieMapper, UtilisateurService utilisateurService, SerieService serieService) {
        this.etiquetteMapper = etiquetteMapper;
        this.serieMapper = serieMapper;
        this.utilisateurService = utilisateurService;
        this.serieService = serieService;
    }

    @Override
    public MediaSelectionneDto mapEntityToDto(SerieSelectionneeEntity input) {
        return new MediaSelectionneDto(
                TypeMedia.SERIE,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                input.getMediaAudioVisuelEntity().getIdTmdb(),
                input.getUtilisateurEntity().getEmail(),
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode());
    }

    @Override
    public SerieSelectionneeEntity mapDtoToEntity(MediaSelectionneDto input) {
        if(input.getEmail()==null) {
            throw new EmailNotFoundInJson("Champ email non trouvé dans le json");
        }

        UtilisateurEntity utilisateur = utilisateurService.trouverUtilisateurParEmail(input.getEmail());
        SerieEntity serie = serieService.trouverSerieParIdTmdb(input.getMediaIdTmdb());

        return new SerieSelectionneeEntity(
                null,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                serie,
                utilisateur,
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode());
    }
}
