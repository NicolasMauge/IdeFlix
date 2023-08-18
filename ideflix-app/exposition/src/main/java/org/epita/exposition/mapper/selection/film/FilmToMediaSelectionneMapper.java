package org.epita.exposition.mapper.selection.film;

import org.epita.application.media.film.FilmService;
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
import org.epita.exposition.dto.selection.MediaSelectionneDto;
import org.springframework.stereotype.Component;

@Component
public class FilmToMediaSelectionneMapper extends Mapper<FilmSelectionneEntity, MediaSelectionneDto> {
    Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    Mapper<FilmEntity, MediaDto> filmMapper;
    UtilisateurService utilisateurService;
    FilmService filmService;

    public FilmToMediaSelectionneMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, Mapper<FilmEntity, MediaDto> filmMapper, UtilisateurService utilisateurService, FilmService filmService) {
        this.etiquetteMapper = etiquetteMapper;
        this.filmMapper = filmMapper;
        this.utilisateurService = utilisateurService;
        this.filmService = filmService;
    }

    @Override
    public MediaSelectionneDto mapEntityToDto(FilmSelectionneEntity input) {
        return new MediaSelectionneDto(
                TypeMedia.FILM,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                input.getMediaAudioVisuelEntity().getIdTmdb(),
                input.getUtilisateurEntity().getEmail(),
                null,
                0,
                null,
                0,
                null);
    }

    @Override
    public FilmSelectionneEntity mapDtoToEntity(MediaSelectionneDto input) {
        if(input.getEmail()==null) {
            throw new EmailNotFoundInJson("Champ email non trouvé dans le json");
        }

        UtilisateurEntity utilisateur = utilisateurService.trouverUtilisateurParEmail(input.getEmail());

        FilmEntity film = filmService.trouverFilmParIdTmdb(input.getMediaIdTmdb());

        return new FilmSelectionneEntity(
                null,
                input.getAvisPouce(),
                input.getDateSelection(),
                this.etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                film,
                utilisateur);
    }
}
