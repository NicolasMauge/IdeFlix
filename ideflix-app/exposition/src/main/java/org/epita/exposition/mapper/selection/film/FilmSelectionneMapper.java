package org.epita.exposition.mapper.selection.film;

import org.epita.application.media.film.FilmService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.dto.selection.FilmSelectionneDto;
import org.springframework.stereotype.Component;

@Component
public class FilmSelectionneMapper extends Mapper<FilmSelectionneEntity, FilmSelectionneDto> {
    private Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    private FilmService filmService;
    private UtilisateurService utilisateurService;

    public FilmSelectionneMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, FilmService filmService, UtilisateurService utilisateurService) {
        this.etiquetteMapper = etiquetteMapper;
        this.filmService = filmService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public FilmSelectionneDto mapEntityToDto(FilmSelectionneEntity input) {
        return new FilmSelectionneDto(
                input.getId(),
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                input.getMediaAudioVisuelEntity().getIdTmdb(),
                input.getUtilisateurEntity().getId()
        );
    }

    @Override
    public FilmSelectionneEntity mapDtoToEntity(FilmSelectionneDto input) {
        FilmEntity film = this.filmService.trouverFilmParIdTmdb(input.getIdTmdb());
        UtilisateurEntity utilisateur = this.utilisateurService.trouverUtilisateurParId(input.getIdUtilisateur());

        return new FilmSelectionneEntity(
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                film,
                utilisateur
        );
    }
}
