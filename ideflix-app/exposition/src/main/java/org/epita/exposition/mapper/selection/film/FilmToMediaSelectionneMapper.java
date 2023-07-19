package org.epita.exposition.mapper.selection.film;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.common.EmailNotFoundInJson;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.MediaDto;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.springframework.stereotype.Component;

@Component
public class FilmToMediaSelectionneMapper extends Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> {
    Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    Mapper<FilmEntity, MediaDto> filmMapper;
    UtilisateurService utilisateurService;

    public FilmToMediaSelectionneMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, Mapper<FilmEntity, MediaDto> filmMapper, UtilisateurService utilisateurService) {
        this.etiquetteMapper = etiquetteMapper;
        this.filmMapper = filmMapper;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public MediaSelectionneCompletDto mapEntityToDto(FilmSelectionneEntity input) {
        return new MediaSelectionneCompletDto(
                input.getId(),
                TypeMedia.FILM,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                this.filmMapper.mapEntityToDto((FilmEntity) input.getMediaAudioVisuelEntity()),
                input.getUtilisateurEntity().getEmail(),
                null,
                0,
                null,
                0,
                null);
    }

    @Override
    public FilmSelectionneEntity mapDtoToEntity(MediaSelectionneCompletDto input) {
        if(input.getEmail()==null) {
            throw new EmailNotFoundInJson("Champ email non trouvé dans le json");
        }

        UtilisateurEntity utilisateur = utilisateurService.trouverUtilisateurParEmail(input.getEmail());

        return new FilmSelectionneEntity(
                input.getId(),
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                this.filmMapper.mapDtoToEntity(input.getMedia()),
                utilisateur);
    }
}
